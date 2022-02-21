#include "GameOptionView.h"
#include <iostream>


namespace Game {


	GameOptionView::GameOptionView(sf::RenderWindow& window) : _window(window) {
		sf::Image bg;
		bg.loadFromFile(CONSTSPACE::DEFAULT_BG);

		_back_ground_texture.loadFromImage(bg);

		_back_ground_sprite.setTexture(_back_ground_texture);
		_back_ground_sprite.setPosition(0, 0);

		_buttons.push_back(new Button(_window, 440, 140, "res/buttons/rand1.png", "res/buttons/rand2.png", 1));
		_buttons.push_back(new Button(_window, 440, 290, "res/buttons/smart1.png", "res/buttons/smart2.png", 2));
		_buttons.push_back(new Button(_window, 440, 440, "res/buttons/friend1.png", "res/buttons/friend2.png", 3));
		_buttons.push_back(new Button(_window, 440, 590, "res/buttons/back1.png", "res/buttons/back2.png", 4));

		_boxes = {};
	}

	void GameOptionView::set_back_ground(const char* bg_path) {
		sf::Image bg;
		bg.loadFromFile(bg_path);

		_back_ground_texture.loadFromImage(bg);

		_back_ground_sprite.setTexture(_back_ground_texture);
		_back_ground_sprite.setPosition(0, 0);
	}

	void GameOptionView::draw() {
		_window.draw(_back_ground_sprite);

		for (auto button : _buttons) {
			button->draw();
		}

		if (_is_name_input) {
			for (auto box : _boxes) {
				box->draw();
			}
		}
	}

	std::vector<Button*> GameOptionView::get_buttons() const {
		return _buttons;
	}

	std::vector<TextBox*> GameOptionView::get_text_boxes() const {
		return _boxes;
	}

	void GameOptionView::reset_view() {
		for (int i = 0; i < _buttons.size(); i++) {
			delete _buttons[i];
		}

		for (int i = 0; i < _boxes.size(); i++) {
			delete _boxes[i];
		}

		_buttons.clear();
		_boxes.clear();
		_is_name_input = false;

		_buttons.push_back(new Button(_window, 440, 140, "res/buttons/rand1.png", "res/buttons/rand2.png", 1));
		_buttons.push_back(new Button(_window, 440, 290, "res/buttons/smart1.png", "res/buttons/smart2.png", 2));
		_buttons.push_back(new Button(_window, 440, 440, "res/buttons/friend1.png", "res/buttons/friend2.png", 3));
		_buttons.push_back(new Button(_window, 440, 590, "res/buttons/back1.png", "res/buttons/back2.png", 4));

		_boxes = {};
	}

	void GameOptionView::add_text_space(int cout) {
		_is_name_input = true;

		for (int i = 0; i < _buttons.size(); i++) {
			delete _buttons[i];
		}

		_buttons.clear();

		_buttons.push_back(new Button(_window, 440, 590, "res/buttons/back1.png", "res/buttons/back2.png", 4));

		if (cout == 1) {
			_boxes.push_back(new TextBox(_window, 480, 150, "Enter player name"));
		}
		else if (cout == 2) {
			_boxes.push_back(new TextBox(_window, 480, 150, "Enter first player name"));
			_boxes.push_back(new TextBox(_window, 480, 250, "Enter second player name"));
		}

	}

	void GameOptionView::add_ok_button() {
		if (_buttons.size() < 2) {
			_buttons.push_back(new Button(_window, 440, 440, "res/buttons/ok1.png", "res/buttons/ok2.png", 5));
		}
	}

	void GameOptionView::remove_ok_button() {
		if (_buttons.size() > 1) {
			delete _buttons.back();
			_buttons.pop_back();
		}
	}

}