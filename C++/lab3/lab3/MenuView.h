#pragma once

#include <SFML/Graphics.hpp>

#include "Button.h"

namespace Game {

	class MenuView  {
	private:
		sf::RenderWindow& _window;

		sf::Texture _back_ground_texture;
		sf::Sprite _back_ground_sprite;

		std::vector<Button*> _buttons;
	public:
		MenuView(sf::RenderWindow&);

		void set_back_ground(const char*);

		void draw();

		std::vector<Button*> get_buttons() const;

		void clear_drawing_obj();

		//~MenuView()
	};
}