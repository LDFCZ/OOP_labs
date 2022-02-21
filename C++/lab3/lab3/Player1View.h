#pragma once

#include <SFML/Graphics.hpp>

#include "Button.h"
#include "Ship.h"
#include "Player1Model.h"

namespace Game {

	class Player1View {
	private:
		sf::RenderWindow& _window;

		Player1Model _model;

		sf::Texture _back_ground_texture;
		sf::Sprite _back_ground_sprite;

		sf::Texture _field_texture;
		sf::Sprite _field_sprite;

		std::vector<Button*> _buttons;
		std::vector<Ship*> _ships;

		sf::Texture _kill_texture;
		sf::Texture _hit_texture;
		sf::Texture _miss_texture;
		std::vector<sf::Sprite*> _kill_labels;
		std::vector<sf::Sprite*> _hit_labels;
		std::vector<sf::Sprite*> _miss_labels;

		std::vector<std::vector<int>> _enemy_field;
		std::vector<std::vector<int>> _player_field;

	public:

		Player1View(sf::RenderWindow&);

		void set_ships_on_field();

		void set_kill_on_field(int, int);

		void set_miss_on_field(int, int);

		void set_hit_on_field(int, int);

		void set_back_ground(const char*);

		void draw();

		std::vector<Button*> get_buttons() const;

		std::vector<Ship*> get_ships() const;

		void reset_view();

		void clear_drawing_obj();

		void process_move();

		Player1Model& get_model();

		//~GamePrepareView();
	};
}

