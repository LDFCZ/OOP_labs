#pragma once

#include "StatisticView.h"
#include "ControllersContext.h"
#include "StatisticModel.h"

namespace Game {

	class StatisticController : public Interfaces::Controller {
	private:
		sf::RenderWindow& _window;

		StatisticView _view;

		StatisticModel _model;

		ControllersContext& _context;

		bool _is_active;
	public:
		StatisticController(sf::RenderWindow&, ControllersContext&);

		Controller* work() override;

		void show_view() override;

		void change_settings(const char*) override;

		void set_active() override;

		void set_inactive() override;

		//~StatisticController();
	};
}

