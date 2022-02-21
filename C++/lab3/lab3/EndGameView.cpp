#include "EndGameView.h"

namespace Game {


	EndGameView::EndGameView(sf::RenderWindow& window) : _window(window) {
		sf::Image bg;
		bg.loadFromFile(CONSTSPACE::DEFAULT_BG);

		_back_ground_texture.loadFromImage(bg);

		_back_ground_sprite.setTexture(_back_ground_texture);
		_back_ground_sprite.setPosition(0, 0);

		_buttons.push_back(new Button(_window, 140, 590, "res/buttons/ok1.png", "res/buttons/ok2.png", 1));
	}

	void EndGameView::set_back_ground(const char* bg_path) {

		sf::Image bg;
		bg.loadFromFile(bg_path);

		_back_ground_texture.loadFromImage(bg);

		_back_ground_sprite.setTexture(_back_ground_texture);
		_back_ground_sprite.setPosition(0, 0);
	}

	void EndGameView::draw() {
		_window.draw(_back_ground_sprite);

		for (auto button : _buttons) {
			button->draw();
		}
	}

	std::vector<Button*> EndGameView::get_buttons() const {
		return _buttons;
	}

}