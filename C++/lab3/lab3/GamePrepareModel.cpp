#include "GamePrepareModel.h"
#include <iostream>

namespace Game {

	bool GamePrepareModel::_check_conclusion(Ship* s, int i, int j) {
		if (!s->get_is_rotate()) {
			if (i + s->get_ship_size() > 10) return false;
			for ( int k = i; k < i + s->get_ship_size(); k ++) {
				if (_field[k][j] != 0) return false;
			}
		}
		else
		{
			if(j + s->get_ship_size() > 10) return false;
			for (int k = j; k < j + s->get_ship_size(); k++) {
				if (_field[i][k] != 0) return false;
			}
		}
		return true;
	}

	void GamePrepareModel::_circle_ship(Ship* s, int i, int j) {
		if (!s->get_is_rotate()) {
			for (int l = 0; l < 3; l++) {
				for (int k = i - 1; k < i + s->get_ship_size() + 1; k++) {
					if (k < 0 || k > 10 || j - 1 + l < 0 || j - 1 + l > 10) continue;
					if (_field[k][j - 1 + l] >= 5) { _field[k][j - 1 + l] = 6; continue; }
					_field[k][j - 1 + l] = 5;
				}
			}
			for (int k = i; k < i + s->get_ship_size(); k++) {
				_field[k][j] = s->get_ship_size();
			}
		}
		else {
			for (int l = 0; l < 3; l++) {
				for (int k = j - 1; k < j + s->get_ship_size() + 1; k++) {
					if (k < 0 || k > 10 || i - 1 + l < 0 || i - 1 + l > 10) continue;
					if(_field[i - 1 + l][k] >= 5) { _field[i - 1 + l][k] = 6; continue;}
					_field[i - 1 + l][k] = 5;
				}
			}
			for (int k = j; k < j + s->get_ship_size(); k++) {
				_field[i][k] = s->get_ship_size();
			}
		}
	}

	void GamePrepareModel::_uncircle_ship(Ship* s, int i, int j) {
		if (!s->get_is_rotate()) {
			for (int l = 0; l < 3; l++) {
				for (int k = i - 1; k < i + s->get_ship_size() + 1; k++) {
					if (k < 0 || k > 10 || j - 1 + l < 0 || j - 1 + l > 10) continue;
					if (_field[k][j - 1 + l] == 6) { _field[k][j - 1 + l] = 5; continue; }
					_field[k][j - 1 + l] = 0;
				}
			}
		}
		else {
			for (int l = 0; l < 3; l++) {
				for (int k = j - 1; k < j + s->get_ship_size() + 1; k++) {
					if (k < 0 || k > 10 || i - 1 + l < 0 || i - 1 + l > 10) continue;
					if (_field[i - 1 + l][k] == 6) { _field[i - 1 + l][k] = 5; continue; }
					_field[i - 1 + l][k] = 0;
				}
			}
		}
	}

	GamePrepareModel::GamePrepareModel() {
		for (int i = 0; i <= 10; i++) {
			_field.push_back({});
			possible_places.push_back({});
			for (int j = 0; j <= 10; j++) {
				_field[i].push_back(0);
				possible_places[i].push_back({ 81 + 40 * j + j, 124 + 40 * i + i });
			}
		}
	}

	std::pair<int, int> GamePrepareModel::get_right_position(Ship* ship, float pos_x, float pos_y) {
		int i_shift = 0, j_shift = 0;
		if (!ship->get_is_rotate()) pos_x -= 40;

		if (!ship->get_is_rotate())
			i_shift = ship->get_ship_size() - 1;
		else
			j_shift = ship->get_ship_size() - 1;
		for (int i = 0; i < 10 - i_shift; i++) {
			for (int j = 0; j < 10 - j_shift; j++) {
				if (pos_x >= possible_places[i][j].first && pos_x < possible_places[i][j+1].first) {
					if (pos_y >= possible_places[i][j].second && pos_y < possible_places[i+1][j].second) {
						if (_check_conclusion(ship, i, j)) {
							if (!ship->get_is_rotate()) return { possible_places[i][j].first+41, possible_places[i][j].second };
							return possible_places[i][j];
						}
						else
							return { 0,0 };
					}
				}
			}
		}
		return { 0,0 };
	}

	void GamePrepareModel::set_ship_on_field(Ship* s) {
		std::pair<int, int> pos = s->get_prev_position();
		if (!s->get_is_rotate()) pos.first -= 41;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (pos.first == possible_places[i][j].first) {
					if (pos.second == possible_places[i][j].second) {
						_circle_ship(s, i, j);	
						return;
					}
					
				}
			}
		}
	}

	void GamePrepareModel::remove_ship_from_field(Ship* s) {
		std::pair<int, int> pos = s->get_prev_position();
		if (!s->get_is_rotate()) pos.first -= 41;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (pos.first == possible_places[i][j].first) {
					if (pos.second == possible_places[i][j].second) {
						_uncircle_ship(s, i, j);
						return;
					}

				}
			}
		}
	}

	void GamePrepareModel::print_field() {
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				std::cout << _field[i][j] << " ";
			}
			std::cout << std::endl;
		}
		std::cout << "--------------------------------------" << std::endl;
	}

	void GamePrepareModel::clear_field() {
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				 _field[i][j] = 0;
			}
			std::cout << std::endl;
		}
	}

	std::vector<std::vector<int>> GamePrepareModel::get_field() {
		return _field;
	}
}