#include "OptionController.h"

namespace Game {


	OptionController::OptionController(sf::RenderWindow& window, ControllersContext& context) :
		_window(window), _context(context), _view(window),  _is_active(false){ }

	Interfaces::Controller* OptionController::work() {
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
					return _context.get_controller_by_name(MenueC);
					break;
				case 2:
					//_mediator.set_new_bg(CONSTSPACE::DEFAULT_BG);
					break;
				case 3:
					//_mediator.set_new_bg(CONSTSPACE::SPECIAL_BG_1);
					break;
				case 4:
					//_mediator.set_new_bg(CONSTSPACE::SPECIAL_BG_2);
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

	void OptionController::show_view() {
		_view.draw();
	}

	void OptionController::change_settings(const char* bg_path) {
		_view.set_back_ground(bg_path);
	}

	void OptionController::set_active() {
		_is_active = true;
	}

	void OptionController::set_inactive() {
		_is_active = false;
	}

}