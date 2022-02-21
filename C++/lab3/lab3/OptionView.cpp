#include "OptionView.h"

namespace Game {

	OptionView::OptionView(sf::RenderWindow& window) : _window(window) {
		sf::Image bg;
		bg.loadFromFile(CONSTSPACE::DEFAULT_BG);

		_back_ground_texture.loadFromImage(bg);

		_back_ground_sprite.setTexture(_back_ground_texture);
		_back_ground_sprite.setPosition(0, 0);

		_buttons.push_back(new Button(_window, 24, 260, "res/theme1.png", 2));
		_buttons.push_back(new Button(_window, 452, 260, "res/theme2.png", 3));
		_buttons.push_back(new Button(_window, 880, 260, "res/theme3.png", 4));
		_buttons.push_back(new Button(_window, 440, 590, "res/buttons/back1.png", "res/buttons/back2.png", 1));
	}

	void OptionView::set_back_ground(const char* bg_path) {
		sf::Image bg;
		bg.loadFromFile(bg_path);

		_back_ground_texture.loadFromImage(bg);

		_back_ground_sprite.setTexture(_back_ground_texture);
		_back_ground_sprite.setPosition(0, 0);
	}

	void OptionView::draw() {
		_window.draw(_back_ground_sprite);

		for (auto button : _buttons) {
			button->draw();
		}
	}

	std::vector<Button*> OptionView::get_buttons() const {
		return _buttons;
	}

	void OptionView::clear_drawing_obj() {
		// TODO
	}

}