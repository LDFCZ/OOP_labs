#include "StatisticView.h"

namespace Game {


	StatisticView::StatisticView(sf::RenderWindow& window) : _window(window) {
		sf::Image bg;
		bg.loadFromFile(CONSTSPACE::DEFAULT_BG);

		_back_ground_texture.loadFromImage(bg);

		_back_ground_sprite.setTexture(_back_ground_texture);
		_back_ground_sprite.setPosition(0, 0);

		_font.loadFromFile("res/18166.otf");


		_text.setString(_model.get_statistic());
		_text.setFont(_font);
		_text.setCharacterSize(40);
		_text.setFillColor(sf::Color::Black);
		_text.setPosition(200, 100);

		_buttons.push_back(new Button(_window, 440, 590, "res/buttons/back1.png", "res/buttons/back2.png", 1));
	}

	void StatisticView::set_back_ground(const char* bg_path) {
		sf::Image bg;
		bg.loadFromFile(bg_path);

		_back_ground_texture.loadFromImage(bg);

		_back_ground_sprite.setTexture(_back_ground_texture);
		_back_ground_sprite.setPosition(0, 0);
	}

	void StatisticView::draw() {
		_window.draw(_back_ground_sprite);
		_window.draw(_text);
		for (auto button : _buttons) {
			button->draw();
		}
	}

	std::vector<Button*> StatisticView::get_buttons() const {
		return _buttons;
	}

	void StatisticView::refresh_statistic() {
		_text.setString(_model.get_statistic());
	}

}