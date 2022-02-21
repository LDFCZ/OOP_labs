#pragma once

#include <SFML/Graphics.hpp>

#include "Button.h"
#include "StatisticModel.h"

namespace Game {

	class StatisticView {
	private:
		sf::RenderWindow& _window;

		sf::Texture _back_ground_texture;
		sf::Sprite _back_ground_sprite;

		std::vector<Button*> _buttons;

		sf::Font _font;
		sf::Text _text;

		StatisticModel _model;

	public:
		StatisticView(sf::RenderWindow&);

		void set_back_ground(const char*);

		void draw();

		std::vector<Button*> get_buttons() const;

		void clear_drawing_obj();

		void refresh_statistic();

		//~StatisticView();
	};
}
