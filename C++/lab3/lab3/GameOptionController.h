#pragma once

#include "GameOptionView.h"
#include "ControllersContext.h"
#include "PlayerControllersContext.h"

namespace Game {

	class GameOptionController : public Interfaces::Controller {
	private:
		sf::RenderWindow& _window;

		GameOptionView _view;

		ControllersContext& _context;

		PlayerControllersContext& _game_context;

		bool _is_active;

		Controller* _check_text_boxes(std::vector<TextBox*>, Player2Type);

	public:
		GameOptionController(sf::RenderWindow&, ControllersContext&, PlayerControllersContext&);

		Controller* work() override;

		void show_view() override;

		void change_settings(const char*) override;

		void set_active() override;

		void set_inactive() override;
	};
}

