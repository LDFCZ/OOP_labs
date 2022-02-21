#pragma once

#include <SFML/Graphics.hpp>

#include "Ship.h"
#include "Button.h"
#include "GamePrepareModel.h"

namespace Game {

	class GamePrepareView {
	private:
		sf::RenderWindow& _window;

		GamePrepareModel _model;

		sf::Texture _back_ground_texture;
		sf::Sprite _back_ground_sprite;

		sf::Texture field_texture;
		sf::Sprite field_sprite;

		std::vector<Button*> _buttons;
		std::vector<Ship*> _ships;

	public:
		GamePrepareView(sf::RenderWindow&);

		void set_back_ground(const char*);

		void draw();

		std::vector<Button*> get_buttons() const;

		std::vector<Ship*> get_ships() const;

		void reset_view();

		void add_ok_button();

		void remove_ok_button();

		void clear_drawing_obj();

		//~GamePrepareView();
	};
}

