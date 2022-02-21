#pragma once

#include <SFML/Graphics.hpp>

#include "Button.h"
#include "TextBox.h"

namespace Game {

	class GameOptionView {
	private:
		sf::RenderWindow& _window;

		sf::Texture _back_ground_texture;
		sf::Sprite _back_ground_sprite;

		std::vector<TextBox*> _boxes;

		std::vector<Button*> _buttons;

		bool _is_name_input = false;
	public:
		GameOptionView(sf::RenderWindow&);

		void set_back_ground(const char*);

		void draw();

		std::vector<Button*> get_buttons() const;

		std::vector<TextBox*> get_text_boxes() const;

		void clear_drawing_obj();

		void reset_view();

		void add_text_space(int);

		void add_ok_button();

		void remove_ok_button();

		//~GameOptionView();
	};
}


