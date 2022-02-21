#pragma once

#include "MenuView.h"
#include "ControllersContext.h"

namespace Game {

	class MenuController : public Interfaces::Controller {
	private:
		sf::RenderWindow& _window;

		MenuView _view;

		ControllersContext& _context;

		bool _is_active;

		Controller* _change_controller(ControllerName);
	public:
		MenuController(sf::RenderWindow&, ControllersContext&);

		Controller* work() override;

		void show_view() override;

		void change_settings(const char*) override;

		void set_active() override;

		void set_inactive() override;
	};
}

