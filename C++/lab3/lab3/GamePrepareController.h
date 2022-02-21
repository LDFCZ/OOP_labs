#pragma once

#include <cstdlib>
#include <intrin.h>
#pragma intrinsic(__rdtsc)

#include "GamePrepareView.h"
#include "ControllersContext.h"
#include "PlayerControllersContext.h"

namespace Game {

	class GamePrepareController : public Interfaces::Controller {
	private:
		sf::RenderWindow& _window;

		GamePrepareView _view;

		GamePrepareModel _model;

		ControllersContext& _context;

		PlayerControllersContext& _game_context;

		bool _is_active;

		bool _remove_flag;

		bool _is_both_players;

		Player2Type _player2_type;

		std::vector<std::vector<int>> _tmp_field;

		void _reset_ships(std::vector<Ship*>);

		bool _check_ships_condition(std::vector<Ship*>, sf::Event, float, float);

		void _random_placement(std::vector<Ship*>);

		bool _end_prepare();

	public:
		GamePrepareController(sf::RenderWindow&, ControllersContext&, PlayerControllersContext&);

		Controller* work() override;

		void show_view() override;

		void change_settings(const char*) override;

		void set_active() override;

		void set_inactive() override;
	};
}

