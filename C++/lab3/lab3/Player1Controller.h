#pragma once

#include "ControllersContext.h"
#include "Player1View.h"

namespace Game {

	class Player1Controller : public Interfaces::Controller, public Interfaces::Player {
	private:
		sf::RenderWindow& _window;

		ControllersContext& _context;

		Player1View _view;

		Player1Model& _model;

		bool _is_active;

		bool _make_move(std::pair<int,int>);

		Ship* _last_died_ship;

		Player2Type t;
	public:
		Player1Controller(sf::RenderWindow&, ControllersContext&);

		Controller* work() override;

		void show_view() override;

		void change_settings(const char*) override;

		void set_active() override;

		void set_inactive() override;

		void set_player_field(const std::vector<std::vector<int>>);

		void set_player_name(std::wstring);

		MoveType get_enemy_move(const int, const int) override;

		Ship* get_last_died_ship() override;

		void set_p2_type(Player2Type);
	};
}

