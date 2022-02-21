#include "Player1Controller.h"
#include <iostream>

namespace Game {


	bool Player1Controller::_make_move(std::pair<int, int> pos) {
		if (!_model.check_move(pos.first, pos.second)) return false;
		MoveType t = _context.get_player2()->get_enemy_move(pos.first, pos.second);
		if (t == Hit) { 
			_model.make_move(pos.first, pos.second, 2);
			_view.process_move();
			return false; 
		} 
		else if (t == Kill) {
			_model.make_move(pos.first, pos.second, 3, _context.get_player2()->get_last_died_ship()->get_ship_size());
			_view.process_move();
			return false;
		}
		else if (t == Miss) {
			_model.make_move(pos.first, pos.second, 1);
			_view.process_move();
		}
		return true;
	}

	Player1Controller::Player1Controller(sf::RenderWindow& window, ControllersContext& context) :
		_window(window), _context(context), _is_active(false), _view(window),  _model(_view.get_model()) { }

	Interfaces::Controller* Player1Controller::work() {
		sf::Vector2i pixelPos;
		sf::Vector2f pos;

		std::vector<Button*> buttons = _view.get_buttons();
		std::vector<Ship*> ships = _view.get_ships();

		int alive_ship_count = 0;
		for ( auto s : ships) {
			if (s->is_alive()) alive_ship_count++;
		}

		if (!alive_ship_count) {
			if (t != Person) _context.set_win_id(2);
			else _context.set_win_id(3);
			set_inactive();
			_model.write_statistic();
			_context.get_controller_by_name(Player2C)->set_inactive();
			_context.get_controller_by_name(EndGameC)->set_active();
			return _context.get_controller_by_name(EndGameC);
		}

		while (_window.isOpen()) {
			_window.clear();

			pixelPos = sf::Mouse::getPosition(_window);
			pos = _window.mapPixelToCoords(pixelPos);

			sf::Event event;
			while (_window.pollEvent(event)) {
				std::pair<int, int> b_pos;
				int id = CONSTSPACE::BAD_ID;
				if (event.type == sf::Event::Closed) {
					_window.close();
					return nullptr;
				}

				for (auto button : buttons) {
					id = button->check_condition(event, pos.x, pos.y);
					if (id != CONSTSPACE::BAD_ID) { 
						b_pos = button->get_position();
						break; 
					}
				}
				switch (id)
				{
				case 1:
					if (_make_move(b_pos))
						return _context.get_controller_by_name(Player2C);
					break;
				case 2:
					set_inactive();
					_context.get_controller_by_name(Player2C)->set_inactive();
					_context.get_controller_by_name(MenueC)->set_active();
					return _context.get_controller_by_name(MenueC);
					break;
				default:
					break;
				}
			}
			this->show_view();
			_window.display();
		}
		return nullptr;
	}

	void Player1Controller::show_view() {
		_view.draw();
	}

	void Player1Controller::change_settings(const char* bg_path) {
		_view.set_back_ground(bg_path);
	}

	void Player1Controller::set_active() {
		_view.set_ships_on_field();
		_is_active = true;
	}

	void Player1Controller::set_inactive() {
		_view.reset_view();
		_is_active = false;
	}

	void Player1Controller::set_player_field(const std::vector<std::vector<int>> field) {
		_model.set_field(field);
	}

	void Player1Controller::set_player_name(std::wstring name) {
		_model.set_name(name);
	}

	MoveType Player1Controller::get_enemy_move(const int pos_x, const int pos_y) {
		std::vector<Ship*> ships = _view.get_ships();
		for (auto ship : ships) {
			std::pair<int, int> p = ship->check_condition(sf::Event(), pos_x + 5 - 707, pos_y + 5);
			if (p.first && p.second) {
				if (ship->is_alive()) {
					_model.take_enemy_move(pos_x, pos_y, 2);
					_view.process_move();
					return Hit;
				}
				else {
					_model.take_enemy_move(pos_x, pos_y, 3, ship->get_ship_size());
					_last_died_ship = ship;
					_view.process_move();

					int alive_ship_count = 0;
					for (auto s : ships) {
						if (s->is_alive()) alive_ship_count++;
					}

					if (!alive_ship_count) {
						return EndGame;
					}

					return Kill;
				}
			}
		}
		_model.take_enemy_move(pos_x, pos_y, 1);
		_view.process_move();
		return Miss;
	}

	Game::Ship* Player1Controller::get_last_died_ship() {
		return _last_died_ship;
	}

	void Player1Controller::set_p2_type(Player2Type p2t) {
		t = p2t;
	}

}