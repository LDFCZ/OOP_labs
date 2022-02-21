#pragma once

#include <map>

#include "Controller.h"
#include "Player.h"
#include "CONSTSPACE.h"

namespace Game {

	enum ControllerName { MenueC, StatisticC, GameC, OptionsC, GameOptionsC, GamePrepareC, Player1C, Player2C, EndGameC };

	class ControllersContext {
	private:
		std::map<ControllerName, Interfaces::Controller*> _controllers;
		int win_id;
		Interfaces::Player* _p1;
		Interfaces::Player* _p2;
	public:
		ControllersContext();

		void add_new_controller(ControllerName, Interfaces::Controller*);

		Interfaces::Controller* get_controller_by_name(ControllerName);

		std::vector<Interfaces::Controller*> get_controllers();

		void chage_controllers_settings(const char*);

		void set_player1(Interfaces::Player*);
		void set_player2(Interfaces::Player*);

		Interfaces::Player* get_player1();
		Interfaces::Player* get_player2();

		int get_win_id();

		void set_win_id(int);

		//~ControllersContext();
	};
}

