#include "Player2Controller.h"
#include <iostream>
namespace Game {


	bool Player2Controller::_make_move(std::pair<int, int> pos) {
		if (!_model.check_move(pos.first, pos.second)) return false;
		MoveType t = _context.get_player1()->get_enemy_move(pos.first, pos.second);
		if (t == Hit) {
			_model.make_move(pos.first, pos.second, 2);
			_view.process_move();
			return false;
		}
		else if (t == Kill) {
			_model.make_move(pos.first, pos.second, 3, _context.get_player1()->get_last_died_ship()->get_ship_size());
			_view.process_move();
			return false;
		}
		else if (t == Miss){
			_model.make_move(pos.first, pos.second, 1);
			_view.process_move();
		}
		return true;
	}

	void Player2Controller::_make_move_bot() {
		if (_type == RandBot) {
			while (true) {
				std::srand(__rdtsc());
				std::vector<std::pair<int, int>> p_p = {};
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						if (_model.check_move(i * 41 + 788, j * 41 + 124)) {
							p_p.push_back({ i, j });
						}
					}
				}
				if (!p_p.size()) return;
				std::pair<int, int> move = p_p[std::rand() % p_p.size()];
				move.first = move.first * 41 + 788;
				move.second = move.second * 41 + 124;
				MoveType t = _context.get_player1()->get_enemy_move(move.first, move.second);
				if (t == Hit) {
					_model.make_move(move.first, move.second, 2);
					_view.process_move();
				}
				else if (t == Kill) {
					_model.make_move(move.first, move.second, 3, _context.get_player1()->get_last_died_ship()->get_ship_size());
					_view.process_move();
				}
				else {
					_model.make_move(move.first, move.second, 1);
					_view.process_move();
					return;
				}
			}
		}
		else {
			while (true) {
				std::pair<int, int> move;
				if (!_is_find) {
					std::srand(__rdtsc());
					std::vector<std::pair<int, int>> p_p = {};
					for (int i = 0; i < 10; i++) {
						for (int j = 0; j < 10; j++) {
							if (_model.check_move(i * 41 + 788, j * 41 + 124)) {
								p_p.push_back({ i, j });
							}
						}
					}
					if (!p_p.size()) return;
					move = p_p[std::rand() % p_p.size()];
				}
				else {
					if (hit_pos.first - 1 < 0) west = false;
					if (hit_pos.first + 1 > 9) east = false;
					if (hit_pos.second - 1 < 0) north = false;
					if (hit_pos.second + 1 > 9) south = false;
					if (west) if(!_model.check_move2((hit_pos.first - 1) * 41 + 788, hit_pos.second * 41 + 124)) west = false;
					if (east) if(!_model.check_move2((hit_pos.first + 1) * 41 + 788, hit_pos.second * 41 + 124)) east = false;
					if (north) if(!_model.check_move2(hit_pos.first * 41 + 788, (hit_pos.second - 1) * 41 + 124)) north = false;
					if (south) if(! _model.check_move2(hit_pos.first  * 41 + 788, (hit_pos.second + 1) * 41 + 124)) south = false;
					int s = 1;
					if (north) {
						while (!_model.check_move((hit_pos.first) * 41 + 788, (hit_pos.second - s) * 41 + 124)) {
							s++;
						} 
						move.first = hit_pos.first;
						move.second = hit_pos.second - s;
						last_d = 1;
					}
					else if (south) {
						while (!_model.check_move((hit_pos.first) * 41 + 788, (hit_pos.second + s) * 41 + 124)) {
							s++;
							if (s > 4) break;
						}
						move.first = hit_pos.first;
						move.second = hit_pos.second + s;
						last_d = 2;
					}
					else if (west) {
						while (!_model.check_move((hit_pos.first - s) * 41 + 788, (hit_pos.second) * 41 + 124)) {
							s++;
							if (s > 4) break;
						}
						move.first = hit_pos.first - s;
						move.second = hit_pos.second;
						last_d = 3;
					}
					else if (east) {
						while (!_model.check_move((hit_pos.first + s) * 41 + 788, (hit_pos.second) * 41 + 124)) {
							s++;
							if (s > 4) break;
						}
						move.first = hit_pos.first + s;
						move.second = hit_pos.second;
						last_d = 4;
					}
					if (!move.first && !move.second)
					{
						_is_find = false;
						north = true;
						south = true;
						west = true;
						east = true;
						std::srand(__rdtsc());
						std::vector<std::pair<int, int>> p_p = {};
						for (int i = 0; i < 10; i++) {
							for (int j = 0; j < 10; j++) {
								if (_model.check_move(i * 41 + 788, j * 41 + 124)) {
									p_p.push_back({ i, j });
								}
							}
						}
						if (!p_p.size()) return;
						move = p_p[std::rand() % p_p.size()];
					}
				}
				move.first = move.first * 41 + 788;
				move.second = move.second * 41 + 124;
				MoveType t = _context.get_player1()->get_enemy_move(move.first, move.second);
				if (t == Hit) {
					_model.make_move(move.first, move.second, 2);
					_view.process_move();
					_is_find = true;
					hit_pos = { (move.first - 788) / 41, (move.second - 124) / 41 };
				}
				else if (t == Kill) {
					_model.make_move(move.first, move.second, 3, _context.get_player1()->get_last_died_ship()->get_ship_size());
					_view.process_move();
					_is_find = false;
					north = true;
					south = true;
					west = true;
					east = true;
				}
				else {
					_model.make_move(move.first, move.second, 1);
					_view.process_move();
					if (_is_find) {
						switch (last_d)
						{
						case 1:
							north = false;
							break;
						case 2:
							south = false;
							break;
						case 3:
							west = false;
							break;
						case 4:
							east = false;
							break;
						}
					}
					return;
				}
			}
		}
	}

	Player2Controller::Player2Controller(sf::RenderWindow& window, ControllersContext& context) :
		_window(window), _context(context), _is_active(false), _view(window), _model(_view.get_model()) { }

	Interfaces::Controller* Player2Controller::work() {
		sf::Vector2i pixelPos;
		sf::Vector2f pos;

		std::vector<Button*> buttons = _view.get_buttons();
		std::vector<Ship*> ships = _view.get_ships();

		int alive_ship_count = 0;
		for (auto s : ships) {
			if (s->is_alive()) alive_ship_count++;
		}

		if (!alive_ship_count) {
			if (_type != Person) _context.set_win_id(1);
			else _context.set_win_id(3);
			_model.write_statistic();
			set_inactive();
			_context.get_controller_by_name(Player1C)->set_inactive();
			_context.get_controller_by_name(EndGameC)->set_active();
			return _context.get_controller_by_name(EndGameC);
		}

		if (_type != Person) {
			_make_move_bot();
			return _context.get_controller_by_name(Player1C);
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
						return _context.get_controller_by_name(Player1C);
						break;
				case 2:
					set_inactive();
					_context.get_controller_by_name(Player1C)->set_inactive();
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

	void Player2Controller::show_view() {
		_view.draw();
	}

	void Player2Controller::change_settings(const char* bg_path) {
		_view.set_back_ground(bg_path);
	}

	void Player2Controller::set_active() {
		_view.set_ships_on_field();
		_is_active = true;
	}

	void Player2Controller::set_inactive() {
		_view.reset_view();
		_is_active = false;
	}

	void Player2Controller::set_player_field(const std::vector<std::vector<int>> field) {
		_model.set_field(field);
	}

	void Player2Controller::set_player_name(std::wstring name) {
		_model.set_name(name);
	}

	void Player2Controller::set_player_type(Player2Type type) {
		_type = type;
	}

	MoveType Player2Controller::get_enemy_move(const int pos_x, const int pos_y) {
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

	Game::Ship* Player2Controller::get_last_died_ship() {
		return _last_died_ship;
	}

}