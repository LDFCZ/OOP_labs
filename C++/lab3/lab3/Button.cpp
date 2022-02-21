#include <iostream>

#include "Button.h"

namespace Game {

	Button::Button(sf::RenderWindow& window,
		float pos_x, float pos_y,
		const char* main_bg_path, const char* special_bg_path, int id) : 
		_window(window) 
	{
		_id = id;
		_pos_x = pos_x;
		_pos_y = pos_y;

		sf::Image main_bg;
		main_bg.loadFromFile(main_bg_path);

		_main_bg_texture.loadFromImage(main_bg);

		_main_bg.setTexture(_main_bg_texture);
		_main_bg.setPosition(pos_x, pos_y);

		sf::Image special_bg;
		special_bg.loadFromFile(special_bg_path);

		_special_bg_texture.loadFromImage(special_bg);

		_special_bg.setTexture(_special_bg_texture);
		_special_bg.setPosition(pos_x, pos_y);

		_is_mouse_on = false;
	}

	Button::Button(sf::RenderWindow& window,
		float pos_x, float pos_y,
		const char* main_bg_path,
		int id) : _window(window) {
		_id = id;
		_pos_x = pos_x;
		_pos_y = pos_y;

		sf::Image main_bg;
		main_bg.loadFromFile(main_bg_path);

		_main_bg_texture.loadFromImage(main_bg);

		_main_bg.setTexture(_main_bg_texture);
		_main_bg.setPosition(pos_x, pos_y);

		sf::Image special_bg;
		special_bg.loadFromFile(main_bg_path);

		_special_bg_texture.loadFromImage(special_bg);

		_special_bg.setTexture(_special_bg_texture);
		_special_bg.setPosition(pos_x, pos_y);

		_is_mouse_on = false;
	}

	void Button::draw() {
		if (_is_mouse_on)
			_window.draw(_special_bg);
		else
			_window.draw(_main_bg);
	}

	int Button::check_condition(sf::Event event, float pos_x, float pos_y) {

		if (_main_bg.getGlobalBounds().contains(pos_x, pos_y)) {
			_is_mouse_on = true;
			if (event.type == sf::Event::MouseButtonPressed)
				if (event.key.code == sf::Mouse::Left) {
					_is_mouse_on = false;
					return _id;
				}
		}
		else {
			_is_mouse_on = false;
		}
		return CONSTSPACE::BAD_ID;
	}

	std::pair<int, int> Button::get_position() const {
		return { _pos_x, _pos_y };
	}

}