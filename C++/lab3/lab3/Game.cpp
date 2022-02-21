#include "Game.h"

namespace Game {

	Game::Game(sf::RenderWindow& window, Interfaces::Controller* controller) : _window(window), _controller(controller) { }

	void Game::run_game() {
		while (_controller) {
			_controller = _controller->work();	
		}
	}
}