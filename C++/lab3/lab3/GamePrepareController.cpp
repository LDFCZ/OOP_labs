#include "GamePrepareController.h"
#include <iostream>

namespace Game {


	void GamePrepareController::_reset_ships(std::vector<Ship*> ships) {
		for (auto ship : ships) {
			_model.remove_ship_from_field(ship);
			ship->set_start_position();
		}
	}

	bool GamePrepareController::_check_ships_condition(std::vector<Ship*> ships, sf::Event event, float pos_x, float pos_y) {
		std::pair<int, int> p;
		int count = 0;
		for (auto ship : ships) {
			p = ship->check_condition(event, pos_x, pos_y);
			if (p.first && p.second) {
				if (ship->get_is_selected()) {
					if (!_remove_flag) {
						_model.remove_ship_from_field(ship);
						_remove_flag = !_remove_flag;
					}
					ship->set_position(p.first, p.second);
				}
				else {
					p = _model.get_right_position(ship, p.first, p.second);
					if (p.first && p.second) {
						ship->set_position(p.first, p.second);
						_model.set_ship_on_field(ship);
						_remove_flag = !_remove_flag;
					}
					else {
						ship->set_prev_position();
						_model.set_ship_on_field(ship);
						_remove_flag = !_remove_flag;
					}
				}
			}
			if (ship->get_is_in_field()) {
				count++;
			}
		}
		if (count == 10) return true;
		return false;
	}

	void GamePrepareController::_random_placement(std::vector<Ship*> ships) {
		_reset_ships(ships);
		std::srand(__rdtsc());
		for (auto ship : ships) {
			std::vector<std::pair<int, int>> possible_places;
			int rotation = std::rand() % 2;
			if (rotation) ship->rotate();
			for ( int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					std::pair<int, int> p;
					p = _model.get_right_position(ship, 81 + 41 * j, 124 + 41 * i);
					if (p.first && p.second) possible_places.push_back(p);
				}
			}
			std::pair<int, int> p = possible_places[std::rand() % possible_places.size()];
			ship->set_position(p.first, p.second);
			_model.set_ship_on_field(ship);
		}
	}

	bool GamePrepareController::_end_prepare() {
		if (_player2_type != Person) {
			std::vector<std::vector<int>> f1 = _model.get_field();
			_reset_ships(_view.get_ships());
			_random_placement(_view.get_ships());
			_game_context.set_battle_fields(f1, _model.get_field());
			return true;
		}
		else {
			if (_is_both_players) {
				_game_context.set_battle_fields(_tmp_field, _model.get_field());
				return true;
			}
			
			_tmp_field = _model.get_field();
			_reset_ships(_view.get_ships());
			_is_both_players = true;
		}
		return false;
	}

	GamePrepareController::GamePrepareController(sf::RenderWindow& window, ControllersContext& context, PlayerControllersContext& game_context) :
	_window(window), _context(context), _game_context(game_context), _is_active(false), _view(window), _remove_flag(false), _is_both_players(false){ }

	Interfaces::Controller* GamePrepareController::work() {
		sf::Vector2i pixelPos;
		sf::Vector2f pos;

		
		std::vector<Ship*> ships = _view.get_ships();

		while (_window.isOpen()) {
			_window.clear();

			pixelPos = sf::Mouse::getPosition(_window);
			pos = _window.mapPixelToCoords(pixelPos);
			std::vector<Button*> buttons = _view.get_buttons();

			sf::Event event;
			while (_window.pollEvent(event)) {
				int id = CONSTSPACE::BAD_ID;
				if (event.type == sf::Event::Closed) {
					_window.close();
					return nullptr;
				}

				if (_check_ships_condition(ships, event, pos.x, pos.y)) {
					_view.add_ok_button();
				}
				else {
					_view.remove_ok_button();
				}

				for (auto button : buttons) {
					id = button->check_condition(event, pos.x, pos.y);
					if (id != CONSTSPACE::BAD_ID) break;
				}
				switch (id)
				{
				case 1:
					set_inactive();
					_context.get_controller_by_name(MenueC)->set_active();
					return _context.get_controller_by_name(MenueC);
					break; // TODO
				case 2:
					_reset_ships(ships);
					break;
				case 3:
					_random_placement(ships);
					break;
				case 4:
					if (_end_prepare()) {
						set_inactive();
						_context.get_controller_by_name(Player1C)->set_active();
						_context.get_controller_by_name(Player2C)->set_active();
						return _context.get_controller_by_name(Player1C);
					}
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

	void GamePrepareController::show_view() {
		_view.draw();
	}

	void GamePrepareController::change_settings(const char* bg_path) {
		_view.set_back_ground(bg_path);
	}

	void GamePrepareController::set_active() {
		_player2_type = _game_context.get_player2_type();
		_is_active = true;
	}

	void GamePrepareController::set_inactive() {
		_is_both_players = false;
		std::vector<Ship*> ships = _view.get_ships();
		_reset_ships(ships);
		_view.reset_view();
		_is_active = false;
	}
}