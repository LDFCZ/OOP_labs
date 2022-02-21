#pragma once

#include <SFML/Graphics.hpp>

#include "CONSTSPACE.h"

namespace Game {

	class TextBox {
	private:
		sf::RenderWindow& _window;

		sf::Font _font;
		sf::RectangleShape _box;
		sf::Text _text_box;
		std::wstring _text;
		std::string _start_str;
		bool _is_selected;

		int _limit = 20;

		void _input_logic(int);

		void _delete_last_char();
	public:
		TextBox(sf::RenderWindow&, float, float, const std::string);

		void draw();

		std::wstring get_text();

		void check_condition(sf::Event, float, float);
	};
}