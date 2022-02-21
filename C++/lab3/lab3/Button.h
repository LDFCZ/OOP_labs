#pragma once

#include <SFML/Graphics.hpp>

#include "CONSTSPACE.h"

namespace Game {

	class Button  {
	private:
		sf::RenderWindow& _window;

		sf::Texture _main_bg_texture;
		sf::Texture _special_bg_texture;

		sf::Sprite _main_bg;
		sf::Sprite _special_bg;

		bool _is_mouse_on;

		int _id;

		float _pos_x;
		float _pos_y;
	public:

		Button(sf::RenderWindow&, float, float, const char*, const char*, int);

		Button(sf::RenderWindow&, float, float, const char*, int);

		void draw();

		int check_condition(sf::Event, float, float);

		std::pair<int, int> get_position() const;
		//~Button();
	};
}
