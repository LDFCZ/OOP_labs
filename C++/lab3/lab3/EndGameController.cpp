#include "EndGameController.h"

namespace Game {


	EndGameController::EndGameController(sf::RenderWindow& window, ControllersContext& context, PlayerControllersContext& game_context) :
		_window(window), _context(context), _game_context(game_context), _view(window), _is_active(false){ }

	Interfaces::Controller* EndGameController::work() {
		sf::Vector2i pixelPos;
		sf::Vector2f pos;

		std::vector<Button*> buttons = _view.get_buttons();

		while (_window.isOpen()) {
			_window.clear();

			pixelPos = sf::Mouse::getPosition(_window);
			pos = _window.mapPixelToCoords(pixelPos);

			sf::Event event;
			while (_window.pollEvent(event)) {
				int id = CONSTSPACE::BAD_ID;
				if (event.type == sf::Event::Closed) {
					_window.close();
					return nullptr;
				}

				for (auto button : buttons) {
					id = button->check_condition(event, pos.x, pos.y);
					if (id != CONSTSPACE::BAD_ID) break;
				}
				switch (id)
				{
				case 1:
					set_inactive();
					_context.get_controller_by_name(MenueC)->set_active();
					return _context.get_controller_by_name(MenueC);
					break;
				
				default:
					break;
				}
			}
			this->show_view();
			_window.display();
		}
		return nullptr;
	}

	void EndGameController::show_view() {
		_view.draw();
	}



	void EndGameController::change_settings(const char*)
	{

	}

	void EndGameController::set_active() {
		win_id = _context.get_win_id();
		switch (win_id)
		{
		case 1:
			_view.set_back_ground("res/victory.png");
			break;
		case 2:
			_view.set_back_ground("res/defeat.png");
			break;
		case 3:
			_view.set_back_ground("res/friend.jpg");
			break;
		}
		_is_active = true;
	}

	void EndGameController::set_inactive() {
		_is_active = false;
	}

}