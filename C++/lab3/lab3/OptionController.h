#pragma once

#include "OptionView.h"
#include "OptionModel.h"
#include "ControllersContext.h"

namespace Game {

	class OptionController : public Interfaces::Controller {
	private:
		sf::RenderWindow& _window;

		OptionView _view;

		OptionModel _model;

		ControllersContext& _context;

		bool _is_active;
	public:
		OptionController(sf::RenderWindow&, ControllersContext&);

		Controller* work() override;

		void show_view() override;

		void change_settings(const char*) override;

		void set_active() override;

		void set_inactive() override;
	};
}
