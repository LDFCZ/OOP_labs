#include "Ship.h"

namespace Game {


	Ship::Ship(sf::RenderWindow& window, int size, const char* alive_path, const char* dead_path, float pos_x, float pos_y) :
		_window(window) {
		_pos_x = pos_x;
		_pos_y = pos_y;
		_prev_pos_x = pos_x;
		_prev_pos_y = pos_y;
		_start_pos_x = pos_x;
		_start_pos_y = pos_y;

		sf::Image a_ship;
		a_ship.loadFromFile(alive_path);

		_texture.loadFromImage(a_ship);

		_sprite.setTexture(_texture);
		_sprite.setPosition(pos_x, pos_y);

		sf::Image d_ship;
		d_ship.loadFromFile(dead_path);

		_dead_texture.loadFromImage(d_ship);

		_dead_sprite.setTexture(_dead_texture);
		_dead_sprite.setPosition(pos_x, pos_y);

		_is_dead = false;
		_is_moveable = true;
		_is_rotate = true;
		_prev_rotation = true;
		_is_selected = false;

		_size = size;
		_hp = size;
	}

	void Ship::draw() {
		if (!_is_dead) {
			_window.draw(_sprite);
		}
		else {
			_window.draw(_dead_sprite);
		}
	}

	void Ship::kill() {
		_is_dead = true;
	}

	void Ship::set_position(float pos_x, float pos_y) {	
		if (!_is_selected) {
			_prev_pos_x = pos_x;
			_prev_pos_y = pos_y;
			_prev_rotation = _is_rotate;
		}
		_pos_x = pos_x;
		_pos_y = pos_y;
		_sprite.setPosition(_pos_x, _pos_y);
		_dead_sprite.setPosition(_pos_x, _pos_y);
	}

	void Ship::rotate() {
		if (_is_rotate) {
			_sprite.rotate(90);
			_dead_sprite.rotate(90);
			_is_rotate = !_is_rotate;
		}
		else {
			_sprite.rotate(-90);
			_dead_sprite.rotate(-90);
			_is_rotate = !_is_rotate;
		}
	}

	void Ship::set_unmoveable() {
		_is_moveable = false;
	}

	void Ship::set_moveable() {
		_is_moveable = true;
	}

	void Ship::set_prev_position() {
		if (_is_rotate != _prev_rotation)
			rotate();
		_pos_x = _prev_pos_x;
		_pos_y = _prev_pos_y;
		_sprite.setPosition(_pos_x, _pos_y);
		_dead_sprite.setPosition(_pos_x, _pos_y);
	}

	void Ship::set_start_position() {
		_pos_x = _start_pos_x;
		_pos_y = _start_pos_y;
		_prev_pos_x = _pos_x;
		_prev_pos_y = _pos_y;
		if (!_is_rotate) rotate();
		_prev_rotation = true;
		_sprite.setPosition(_pos_x, _pos_y);
		_dead_sprite.setPosition(_pos_x, _pos_y);
	}

	std::pair<int, int> Ship::check_condition(sf::Event event, float pos_x, float pos_y) {
		if (_is_moveable) {
			if (_is_selected) {
				if (event.type == sf::Event::MouseButtonPressed)
					if (event.key.code == sf::Mouse::Right) {
						rotate();	
					}
				if (event.type == sf::Event::MouseButtonPressed)
					if (event.key.code == sf::Mouse::Left) {
						_is_selected = false;
					}
				std::pair<int, int> pos;
				if (!_is_rotate)
					pos = { pos_x + 40, pos_y };
				else
					pos = { pos_x, pos_y };
				return pos;
			}
			if (_sprite.getGlobalBounds().contains(pos_x, pos_y)) {
				if (event.type == sf::Event::MouseButtonPressed) {
					if (event.key.code == sf::Mouse::Left) {
						if (!_is_selected) {
							_is_selected = true;
							std::pair<int, int> pos;
							if (!_is_rotate)
								pos = { pos_x + 40, pos_y };
							else
								pos = { pos_x, pos_y };
							return pos;
						}
					}
				}
			}
		}
		else {
			if (_sprite.getGlobalBounds().contains(pos_x, pos_y)) {
				_hp -= 1;
				if (!_hp) kill();
				return { 1, 1 };
			}
		}
		return { 0, 0 };
	}

	bool Ship::get_is_selected() const {
		return _is_selected;
	}

	std::pair<int, int> Ship::get_prev_position() const {
		return { _prev_pos_x, _prev_pos_y };
	}

	int Ship::get_ship_size() const {
		return _size;
	}

	bool Ship::get_is_rotate() const {
		return _is_rotate;
	}

	bool Ship::get_is_in_field() const {
		if (_pos_x != _start_pos_x && _pos_y != _start_pos_y) return true;
		return false;
	}

	bool Ship::is_alive() const {
		return !_is_dead;
	}

}