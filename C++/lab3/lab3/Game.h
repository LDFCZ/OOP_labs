#pragma once

#include <SFML/Graphics.hpp>

#include "CONSTSPACE.h"
#include "ControllersContext.h"

namespace Game {

	class Game {
	private:
		sf::RenderWindow& _window;

		Interfaces::Controller* _controller;
	public:

		Game(sf::RenderWindow&, Interfaces::Controller*);

		void run_game();
	};
}

