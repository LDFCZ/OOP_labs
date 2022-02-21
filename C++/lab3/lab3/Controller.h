#pragma once

#include <SFML/Graphics.hpp>

namespace Game {
	enum Player2Type { RandBot, CleverBot, Person };
}

namespace Interfaces {

	class Controller {
	public:
		virtual Controller* work() = 0;

		virtual void show_view() = 0;

		virtual void change_settings(const char*) = 0;

		virtual void set_active() = 0;

		virtual void set_inactive() = 0;
	};
}