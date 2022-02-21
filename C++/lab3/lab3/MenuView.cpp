#include "MenuView.h"


namespace Game {


	MenuView::MenuView(sf::RenderWindow& window) : _window(window) {
		sf::Image bg;
		bg.loadFromFile(CONSTSPACE::DEFAULT_BG);

		_back_ground_texture.loadFromImage(bg);

		_back_ground_sprite.setTexture(_back_ground_texture);
		_back_ground_sprite.setPosition(0, 0);

		_buttons.push_back(new Button(_window, 440, 140, "res/buttons/play1.png", "res/buttons/play2.png", 1));
		_buttons.push_back(new Button(_window, 440, 290, "res/buttons/stat1.png", "res/buttons/stat2.png", 2));
		_buttons.push_back(new Button(_window, 440, 440, "res/buttons/opt1.png", "res/buttons/opt2.png", 3));
		_buttons.push_back(new Button(_window, 440, 590, "res/buttons/quit1.png", "res/buttons/quit2.png", 4));
	}

	void MenuView::set_back_ground(const char* bg_path) {

		sf::Image bg;
		bg.loadFromFile(bg_path);

		_back_ground_texture.loadFromImage(bg);

		_back_ground_sprite.setTexture(_back_ground_texture);
		_back_ground_sprite.setPosition(0, 0);
	}

	void MenuView::draw() {
		_window.draw(_back_ground_sprite);

		for (auto button : _buttons) {
			button->draw();
		}
	}

	std::vector<Button*> MenuView::get_buttons() const {
		return _buttons;
	}

}