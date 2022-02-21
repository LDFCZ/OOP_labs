#include "MenuController.h"

namespace Game {


	Interfaces::Controller* MenuController::_change_controller(ControllerName name) {
		Controller* ret_c;
		ret_c = _context.get_controller_by_name(name);
		ret_c->set_active();
		this->set_inactive();
		return ret_c;
	}

	MenuController::MenuController(sf::RenderWindow& window, ControllersContext& context) :
		_window(window), _context(context), _is_active(false), _view(window){ }

	Interfaces::Controller* MenuController::work() {
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
					return _change_controller(GameOptionsC);
					break;
				case 2:
					return _change_controller(StatisticC);
					break;
				case 3:
					return _change_controller(OptionsC);
					break;
				case 4:
					_window.close();
					return nullptr;
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

	void MenuController::show_view() {
		_view.draw();
	}

	void MenuController::change_settings(const char* bg_path) {
		_view.set_back_ground(bg_path);
	}

	void MenuController::set_active() {
		_is_active = true;
	}

	void MenuController::set_inactive() {
		_is_active = false;
	}

}