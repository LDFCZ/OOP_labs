#pragma once

#include <SFML/Graphics.hpp>

namespace Game {

	class Ship {
	private:
		sf::RenderWindow& _window;


		int _size;
		int _hp;

		sf::Texture _texture;
		sf::Sprite _sprite;

		sf::Texture _dead_texture;
		sf::Sprite _dead_sprite;

		bool _is_selected;

		bool _is_moveable;

		bool _is_dead;

		float _pos_x;
		float _pos_y;
		float _prev_pos_x;
		float _prev_pos_y;
		float _start_pos_x;
		float _start_pos_y;

		bool _is_rotate;
		bool _prev_rotation;

	public:

		Ship(sf::RenderWindow&, int, const char*, const char*, float, float);

		void draw();

		void kill();

		void set_position(float, float);

		void rotate();

		void set_unmoveable();

		void set_moveable();

		void set_prev_position();

		void set_start_position();

		std::pair<int, int> check_condition(sf::Event, float, float);

		bool get_is_selected() const;

		std::pair<int, int> get_prev_position() const;

		int get_ship_size() const;

		bool get_is_rotate() const;

		bool get_is_in_field() const;

		bool is_alive() const;
	};
}

