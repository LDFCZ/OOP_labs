#include "GameOptionController.h"

namespace Game {


	Interfaces::Controller* GameOptionController::_check_text_boxes(std::vector<TextBox*> boxes, Player2Type p2_type) {
		if (boxes.size() == 1) {
			if (boxes[0]->get_text().length() != 0) {
				_game_context.set_names(boxes[0]->get_text());
				set_inactive();
				_game_context.set_player2_type(p2_type);
				_context.get_controller_by_name(GamePrepareC)->set_active();
				return _context.get_controller_by_name(GamePrepareC);
			}
		} else if (boxes.size() == 2) {
			if (boxes[0]->get_text().length() != 0 && boxes[1]->get_text().length() != 0) {
				_game_context.set_names(boxes[0]->get_text(), boxes[1]->get_text());
				set_inactive();
				_game_context.set_player2_type(p2_type);
				_context.get_controller_by_name(GamePrepareC)->set_active();
				return _context.get_controller_by_name(GamePrepareC);
			}
		}
		return this;
	}

	GameOptionController::GameOptionController(sf::RenderWindow& window, ControllersContext& context, PlayerControllersContext& game_context) :
		_window(window), _context(context), _view(GameOptionView(_window)), _game_context(game_context), _is_active(false) { }

	Interfaces::Controller* GameOptionController::work() {

		sf::Vector2i pixelPos;
		sf::Vector2f pos;
		Player2Type p2_type = RandBot;
		while (_window.isOpen()) {
			_window.clear();

			pixelPos = sf::Mouse::getPosition(_window);
			pos = _window.mapPixelToCoords(pixelPos);

			std::vector<Button*> buttons = _view.get_buttons();
			std::vector<TextBox*> boxes = _view.get_text_boxes();

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

				for (auto box : boxes) {
					if (box->get_text().length() != 0) {
						_view.add_ok_button();
					}
					else {
						_view.remove_ok_button();
					}
					box->check_condition(event, pos.x, pos.y);
				}

				switch (id)
				{
				case 1:
					_view.add_text_space(1);
					p2_type = RandBot;
					break;
				case 2:
					_view.add_text_space(1);
					p2_type = CleverBot;
					break;
				case 3:
					_view.add_text_space(2);
					p2_type = Person;
					break;
				case 4:
					set_inactive();
					return _context.get_controller_by_name(MenueC);
					break;
				case 5:	
					return _check_text_boxes(boxes, p2_type);
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

	void GameOptionController::show_view() {
		_view.draw();
	}

	void GameOptionController::change_settings(const char* bg_path) {
		_view.set_back_ground(bg_path);
	}

	void GameOptionController::set_active() {
		_is_active = true;
	}

	void GameOptionController::set_inactive() {
		_view.reset_view();
		_is_active = false;
	}

}