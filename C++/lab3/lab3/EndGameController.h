#pragma once

#include "EndGameView.h"
#include "ControllersContext.h"
#include "PlayerControllersContext.h"

namespace Game {

	class EndGameController : public Interfaces::Controller {
	private:
		sf::RenderWindow& _window;

		EndGameView _view;

		ControllersContext& _context;

		PlayerControllersContext& _game_context;
		int win_id;
		bool _is_active;

	public:
		EndGameController(sf::RenderWindow&, ControllersContext&, PlayerControllersContext&);

		Controller* work() override;

		void show_view() override;

		void change_settings(const char*) override;

		void set_active() override;

		void set_inactive() override;
	};
}

