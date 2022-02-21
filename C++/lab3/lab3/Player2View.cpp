#include "Player2View.h"
#include <iostream>

namespace Game {

	Player2View::Player2View(sf::RenderWindow& window) : _window(window) {
		sf::Image bg;
		bg.loadFromFile("res/bg.png");

		_back_ground_texture.loadFromImage(bg);

		_back_ground_sprite.setTexture(_back_ground_texture);
		_back_ground_sprite.setPosition(0, 0);

		sf::Image field;
		field.loadFromFile("res/net.png");

		_field_texture.loadFromImage(field);

		_field_sprite.setTexture(_field_texture);
		_field_sprite.setPosition(0, -30);

		_buttons.push_back(new Button(_window, 850, 590, "res/buttons/back1.png", "res/buttons/back2.png", 2));
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				_buttons.push_back(new Button(_window, 788 + 41 * j, 124 + 41 * i, "res/buttons/p1.png", "res/buttons/p3.png", 1));
			}
		}

		sf::Image hit;
		hit.loadFromFile("res/kill.png");

		_hit_texture.loadFromImage(hit);

		sf::Image miss;
		miss.loadFromFile("res/miss.png");

		_miss_texture.loadFromImage(miss);

		sf::Image kill;
		kill.loadFromFile("res/die.png");

		_kill_texture.loadFromImage(kill);

		_ships = {};

		for (int i = 0; i < 10; i++) {
			_enemy_field.push_back({});
			for (int j = 0; j < 10; j++) {
				_enemy_field[i].push_back(0);
			}
		}
	}

	void Player2View::set_ships_on_field() {
		reset_view();
		std::vector<std::vector<int>> field = _model.get_field();
		_player_field = field;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				switch (field[i][j])
				{
				case 1:
					_ships.push_back(new Ship(_window, 1, "res/ships/1deck_clear.png", "res/ships/1deck_kaput.png", 81 + 41 * j, 124 + 41 * i));
					field[i][j] = 0;
					_ships[_ships.size() - 1]->set_unmoveable();
					break;
				case 2:
					_ships.push_back(new Ship(_window, 2, "res/ships/2deck_clear.png", "res/ships/2deck_kaput.png", 81 + 41 * j, 124 + 41 * i));
					if (j + 1 == 10 || field[i][j + 1] == 5 || field[i][j + 1] == 6) {
						_ships[_ships.size() - 1]->rotate();
						_ships[_ships.size() - 1]->set_position(81 + 41 * j + 40, 124 + 41 * i);
						for (int k = i; k < i + 2; k++) field[k][j] = 0;
						_ships[_ships.size() - 1]->set_unmoveable();
						break;
					}
					for (int k = j; k < j + 2; k++) field[i][k] = 0;
					_ships[_ships.size() - 1]->set_unmoveable();
					break;
				case 3:
					_ships.push_back(new Ship(_window, 3, "res/ships/3deck_clear.png", "res/ships/3deck_kaput.png", 81 + 41 * j, 124 + 41 * i));
					if (j + 1 == 10 || field[i][j + 1] == 5 || field[i][j + 1] == 6) {
						_ships[_ships.size() - 1]->rotate();
						_ships[_ships.size() - 1]->set_position(81 + 41 * j + 40, 124 + 41 * i);
						for (int k = i; k < i + 3; k++) field[k][j] = 0;
						_ships[_ships.size() - 1]->set_unmoveable();
						break;
					}
					for (int k = j; k < j + 3; k++) field[i][k] = 0;
					_ships[_ships.size() - 1]->set_unmoveable();
					break;
				case 4:
					_ships.push_back(new Ship(_window, 4, "res/ships/4deck_clear.png", "res/ships/4deck_kaput.png", 81 + 41 * j, 124 + 41 * i));
					if (j + 1 == 10 || field[i][j + 1] == 5 || field[i][j + 1] == 6) {
						_ships[_ships.size() - 1]->rotate();
						_ships[_ships.size() - 1]->set_position(81 + 41 * j + 40, 124 + 41 * i);
						for (int k = i; k < i + 4; k++) field[k][j] = 0;
						_ships[_ships.size() - 1]->set_unmoveable();
						break;
					}
					for (int k = j; k < j + 4; k++) field[i][k] = 0;
					_ships[_ships.size() - 1]->set_unmoveable();
					break;
				default:
					break;
				}
			}
		}
	}

	void Player2View::set_kill_on_field(int pos_x, int pos_y) {
		sf::Sprite* s = new sf::Sprite;
		s->setPosition(pos_x, pos_y);
		s->setTexture(_kill_texture);
		_kill_labels.push_back(s);
	}

	void Player2View::set_miss_on_field(int pos_x, int pos_y) {
		sf::Sprite* s = new sf::Sprite;
		s->setPosition(pos_x, pos_y);
		s->setTexture(_miss_texture);
		_miss_labels.push_back(s);
	}

	void Player2View::set_hit_on_field(int pos_x, int pos_y) {
		sf::Sprite* s = new sf::Sprite;
		s->setPosition(pos_x, pos_y);
		s->setTexture(_hit_texture);
		_hit_labels.push_back(s);
	}

	void Player2View::set_back_ground(const char* bg_path) {
		sf::Image bg;
		bg.loadFromFile(bg_path);

		_back_ground_texture.loadFromImage(bg);

		_back_ground_sprite.setTexture(_back_ground_texture);
		_back_ground_sprite.setPosition(0, 0);
	}

	void Player2View::draw() {
		_window.draw(_back_ground_sprite);
		_window.draw(_field_sprite);

		for (auto ship : _ships) {
			ship->draw();
		}

		for (auto button : _buttons) {
			button->draw();
		}

		for (auto lable : _hit_labels) {
			_window.draw(*lable);
		}
		for (auto lable : _miss_labels) {
			_window.draw(*lable);
		}
		for (auto lable : _kill_labels) {
			_window.draw(*lable);
		}
	}

	std::vector<Button*> Player2View::get_buttons() const {
		return _buttons;
	}

	std::vector<Ship*> Player2View::get_ships() const {
		return _ships;
	}

	void Player2View::reset_view() {
		for (int i = _miss_labels.size(); i > 0; i--) {
			delete _miss_labels[i - 1];
			_miss_labels.pop_back();
		}

		for (int i = _hit_labels.size(); i > 0; i--) {
			delete _hit_labels[i - 1];
			_hit_labels.pop_back();
		}

		for (int i = _kill_labels.size(); i > 0; i--) {
			delete _kill_labels[i - 1];
			_kill_labels.pop_back();
		}

		for (int i = _ships.size(); i > 0; i--) {
			delete _ships[i - 1];
			_ships.pop_back();
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				_enemy_field[i][j] = 0;
			}
		}
	}

	void Player2View::process_move() {
		std::vector<std::vector<int>> e_f = _model.get_enemy_field();
		std::vector<std::vector<int>> p_f = _model.get_field();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (e_f[i][j] != _enemy_field[i][j]) {
					_enemy_field[i][j] = e_f[i][j];
					if (e_f[i][j] == 7) set_miss_on_field(j * 41 + 788, 124 + 41 * i);
					if (e_f[i][j] == 8) set_hit_on_field(j * 41 + 788, 124 + 41 * i);
					if (e_f[i][j] == 9) set_kill_on_field(j * 41 + 788, 124 + 41 * i);
				}
				if (p_f[i][j] != _player_field[i][j]) {
					_player_field[i][j] = p_f[i][j];
					if (p_f[i][j] == 7) set_miss_on_field(j * 41 + 81, 124 + 41 * i);
					if (p_f[i][j] == 8) set_hit_on_field(j * 41 + 81, 124 + 41 * i);
					if (p_f[i][j] == 9) set_kill_on_field(j * 41 + 81, 124 + 41 * i);
				}
			}
		}
	}

	Game::Player2Model& Player2View::get_model() {
		return _model;
	}

}