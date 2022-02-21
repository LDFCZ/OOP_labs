#include "StatisticController.h"

namespace Game {


	StatisticController::StatisticController(sf::RenderWindow& window, ControllersContext& context) :
		_window(window), _context(context), _is_active(false), _view(window) { }

	Interfaces::Controller* StatisticController::work() {
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
					Controller* ret_c;
					ret_c = _context.get_controller_by_name(MenueC);
					ret_c->set_active();
					this->set_inactive();
					return ret_c;
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

	void StatisticController::show_view() {
		_view.draw();
	}

	void StatisticController::change_settings(const char* bg_path) {
		_view.set_back_ground(bg_path);
	}

	void StatisticController::set_active() {
		_view.refresh_statistic();
		_is_active = true;
	}

	void StatisticController::set_inactive() {
		_is_active = false;
	}

}