#pragma once

#include <cstdlib>
#include <intrin.h>
#pragma intrinsic(__rdtsc)

#include "ControllersContext.h"
#include "Player2View.h"

namespace Game {

	class Player2Controller : public Interfaces::Controller, public Interfaces::Player{
	private:
		sf::RenderWindow& _window;

		ControllersContext& _context;

		Player2View _view;

		Player2Model& _model;

		Player2Type _type;

		bool _is_active;

		Ship* _last_died_ship;

		bool _make_move(std::pair<int, int>);

		void _make_move_bot();

		bool _is_find = false;

		bool north = true;
		bool south = true;
		bool west = true;
		bool east = true;
		std::pair<int, int> hit_pos;
		int last_d;

	public:
		Player2Controller(sf::RenderWindow&, ControllersContext&);

		Controller* work() override;

		void show_view() override;

		void change_settings(const char*) override;

		void set_active() override;

		void set_inactive() override;

		void set_player_field(const std::vector<std::vector<int>>);

		void set_player_name(std::wstring);

		void set_player_type(Player2Type);

		MoveType get_enemy_move(const int, const int) override;

		Ship* get_last_died_ship() override;
	};
}

