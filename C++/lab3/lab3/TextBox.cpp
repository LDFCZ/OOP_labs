#include "TextBox.h"

namespace Game {


	void TextBox::_input_logic(int char_typed) {

		if (char_typed == CONSTSPACE::DELETE_KEY) {
			_delete_last_char();
		}
		if (char_typed != CONSTSPACE::DELETE_KEY && char_typed != CONSTSPACE::ESCAPE_KEY && char_typed != CONSTSPACE::ENTER_KEY) {
			_text += wchar_t(char_typed);
		}
	}

	void TextBox::_delete_last_char() {
		if (_text.size() > 0)  _text.resize(_text.size() - 1);
		_text_box.setString(_text);
	}

	TextBox::TextBox(sf::RenderWindow& window, float pos_x, float pos_y, const std::string start_str) : _window(window) {
		_font.loadFromFile("res/18166.otf");

		_box.setSize(sf::Vector2f(320, 50));
		_box.setOutlineColor(sf::Color::Blue);
		_box.setOutlineThickness(5);
		_box.setPosition(pos_x, pos_y);
		_start_str = start_str;
		_text_box.setString(start_str);
		_text_box.setFont(_font);
		_text_box.setCharacterSize(20);
		_text_box.setFillColor(sf::Color::Black);
		_text_box.setPosition(pos_x + 10, pos_y + 15);
	}

	void TextBox::draw() {
		_window.draw(_box);
		_window.draw(_text_box);
	}



	std::wstring TextBox::get_text() {
		return _text;
	}

	void TextBox::check_condition(sf::Event event, float pos_x, float pos_y) {
		if (event.type == sf::Event::MouseButtonPressed && event.key.code == sf::Mouse::Left) {
			if (_box.getGlobalBounds().contains(pos_x, pos_y)) {
				_is_selected = true;
			}
			else {
				_is_selected = false;
			}
		}

		if (_is_selected) {
			_text_box.setString(_text + L"_");
			if (event.type == sf::Event::TextEntered) {
				int char_typed = event.text.unicode;
				if (_text.length() < _limit && char_typed != CONSTSPACE::DELETE_KEY) {
					_input_logic(char_typed);
				}
				else if (char_typed == CONSTSPACE::DELETE_KEY) {
					_delete_last_char();
				}
			}
		}
		else {
			if (_text.length()) {
				_text_box.setString(_text);
			}
			else {
				_text_box.setString(_start_str);
			}
		}
	}

}