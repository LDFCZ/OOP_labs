#include "GamePrepareView.h"

namespace Game {

	GamePrepareView::GamePrepareView(sf::RenderWindow& window) : _window(window) {
		sf::Image bg;
		bg.loadFromFile("res/bg.png");

		_back_ground_texture.loadFromImage(bg);

		_back_ground_sprite.setTexture(_back_ground_texture);
		_back_ground_sprite.setPosition(0, 0);

		sf::Image field;
		field.loadFromFile("res/net.png");

		field_texture.loadFromImage(field);

		field_sprite.setTexture(field_texture);
		field_sprite.setPosition(0, -30);

		_buttons.push_back(new Button(_window, 850, 590, "res/buttons/back1.png", "res/buttons/back2.png", 1));
		_buttons.push_back(new Button(_window, 200, 590, "res/buttons/refresh1.png", "res/buttons/refresh2.png", 2));
		_buttons.push_back(new Button(_window, 300, 590, "res/buttons/randb1.png", "res/buttons/rand2b.png", 3));

		_ships.push_back(new Ship(_window, 4, "res/ships/4deck_clear.png", "res/ships/4deck_kaput.png", 550, 95));
		_ships.push_back(new Ship(_window, 3, "res/ships/3deck_clear.png", "res/ships/3deck_kaput.png", 550, 145));
		_ships.push_back(new Ship(_window, 3, "res/ships/3deck_clear.png", "res/ships/3deck_kaput.png", 550, 185));
		_ships.push_back(new Ship(_window, 2, "res/ships/2deck_clear.png", "res/ships/2deck_kaput.png", 550, 235));
		_ships.push_back(new Ship(_window, 2, "res/ships/2deck_clear.png", "res/ships/2deck_kaput.png", 550, 285));
		_ships.push_back(new Ship(_window, 2, "res/ships/2deck_clear.png", "res/ships/2deck_kaput.png", 550, 335));
		_ships.push_back(new Ship(_window, 1, "res/ships/1deck_clear.png", "res/ships/1deck_kaput.png", 550, 385));
		_ships.push_back(new Ship(_window, 1, "res/ships/1deck_clear.png", "res/ships/1deck_kaput.png", 550, 435));
		_ships.push_back(new Ship(_window, 1, "res/ships/1deck_clear.png", "res/ships/1deck_kaput.png", 550, 485));
		_ships.push_back(new Ship(_window, 1, "res/ships/1deck_clear.png", "res/ships/1deck_kaput.png", 550, 535));
	}

	void GamePrepareView::set_back_ground(const char* bg_path) {
		sf::Image bg;
		bg.loadFromFile(bg_path);

		_back_ground_texture.loadFromImage(bg);

		_back_ground_sprite.setTexture(_back_ground_texture);
		_back_ground_sprite.setPosition(0, 0);
	}

	void GamePrepareView::draw() {
		_window.draw(_back_ground_sprite);
		_window.draw(field_sprite);
		for (auto button : _buttons) {
			button->draw();
		}
		Ship* s = nullptr;
		for (auto ship : _ships) {
			ship->draw();
			if (ship->get_is_selected()) s = ship;
		}
		if (s)
			s->draw();
	}

	std::vector<Button*> GamePrepareView::get_buttons() const {
		return _buttons;
	}

	std::vector<Ship*> GamePrepareView::get_ships() const {
		return _ships;
	}

	void GamePrepareView::reset_view() {
		for (auto ship : _ships) {
			ship->set_moveable();
			ship->set_start_position();
		}
	}

	void GamePrepareView::add_ok_button() {
		if (_buttons.size() < 4)
			_buttons.push_back(new Button(_window, 440, 590, "res/buttons/ok1.png", "res/buttons/ok2.png", 4));
	}

	void GamePrepareView::remove_ok_button() {
		if (_buttons.size() == 4) {
			delete _buttons[_buttons.size() - 1];
			_buttons.pop_back();
		}
	}

}