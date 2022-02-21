#include "Player2Model.h"
#include <iostream>

namespace Game {

	std::vector<std::pair<std::wstring, int>> Player2Model::_read_stat(std::wifstream& file) const {
		std::wstring line, word;
		int i = 0;
		std::vector<std::pair<std::wstring, int>> ret_v;
		while (getline(file, line)) {
			if (i++ > 20) break;
			word.clear();
			std::pair<std::wstring, int> p;
			for (size_t i = 0; i < line.length(); i++) {
				if (line[i] != L',')
					word += line[i];
				else {
					p.first = word;
					word.clear();
				}
			}
			p.second = stoi(word);
			ret_v.push_back(p);
		}
		return ret_v;
	}

	void Player2Model::_circle_ship(std::vector<std::vector<int>>& field, int y, int x, int size)
	{
		int i = 1;
		while (true) {
			if (x - i >= 0) {
				if (field[x - i][y] == 8) i++;
				else break;
			}
			else
				break;
		}
		if (i != 1)
			x -= i - 1;
		i = 1;
		while (true) {
			if (y - i >= 0) {
				if (field[x][y - i] == 8) i++;
				else break;
			}
			else
				break;
		}
		if (i != 1)
			y -= i - 1;
		if (y + 1 < 10 && field[x][y + 1] == 8) {
			for (int i = x - 1; i < x + 2; i++) {
				for (int j = y - 1; j < y + size + 1; j++) {

					if (i > 9 || i < 0 || j > 9 || j < 0) {
						continue;
					}
					if (field[i][j] == 8) field[i][j] = 9;
					else field[i][j] = 7;
				}
			}
		}
		else {
			for (int i = x - 1; i < x + size + 1; i++) {
				for (int j = y - 1; j < y + 2; j++) {

					if (i > 9 || i < 0 || j > 9 || j < 0) {
						continue;
					}
					if (field[i][j] == 8) field[i][j] = 9;
					else field[i][j] = 7;
				}
			}
		}
	}

	void Player2Model::set_field(const std::vector<std::vector<int>> field) {
		if (_player_field.size()) {
			for (auto p : _player_field) p.clear();
			_player_field.clear();
			for (auto p : _enemy_field) p.clear();
			_enemy_field.clear();
		}

		for (int i = 0; i < 10; i++) {
			_player_field.push_back({});
			_enemy_field.push_back({});
			for (int j = 0; j < 10; j++) {
				_enemy_field[i].push_back(0);
				_player_field[i].push_back(field[i][j]);
			}
		}
	}

	std::vector<std::vector<int>> Player2Model::get_field() const {
		return _player_field;
	}

	void Player2Model::set_name(const std::wstring name) {
		_name = name;
	}

	void Player2Model::write_statistic() {
		std::vector<std::pair<std::wstring, int>> stat;

		std::wifstream in_file;
		const std::locale utf8_locale = std::locale(std::locale(), new std::codecvt_utf8<wchar_t>());
		in_file.open(_file_name.c_str());
		in_file.imbue(utf8_locale);

		if (!in_file.is_open()) {
			in_file.close();
			return;
		}

		stat = _read_stat(in_file);

		in_file.close();

		std::wofstream out_file;
		out_file.open(_file_name.c_str());
		out_file.imbue(utf8_locale);

		if (!out_file.is_open()) {
			out_file.close();
			return;
		}

		bool is_in_stat = false;

		for (int i = 0; i < stat.size(); i++) {
			if (stat[i].first == _name) {
				stat[i].second += 1;
				is_in_stat = true;
				break;
			}
		}
		if (!is_in_stat) stat.push_back({ _name, 1 });

		std::sort(stat.begin(), stat.end(),
			[](auto& left, auto& right) {
				return left.second > right.second;
			});

		for (auto s : stat) {
			out_file << s.first << L',' << s.second << L'\n';
		}
		out_file.close();
	}

	bool Player2Model::check_move(int pos_x, int pos_y) {
		if (_enemy_field[(pos_y - 124) / 41][(pos_x - 788) / 41] != 0) return false;
		return true;
	}

	bool Player2Model::check_move2(int pos_x, int pos_y) {
		if (_enemy_field[(pos_y - 124) / 41][(pos_x - 788) / 41] == 0 || _enemy_field[(pos_y - 124) / 41][(pos_x - 788) / 41] == 2) return true;
		return false;
	}

	bool Player2Model::make_move(int pos_x, int pos_y, int type, int size) {
		if (type == 1) {
			_enemy_field[(pos_y - 124) / 41][(pos_x - 788) / 41] = 7;
		}
		else if (type == 2) {
			_enemy_field[(pos_y - 124) / 41][(pos_x - 788) / 41] = 8;
		}
		else if (type == 3) {
			_enemy_field[(pos_y - 124) / 41][(pos_x - 788) / 41] = 8;
			_circle_ship(_enemy_field, (pos_x - 788) / 41, (pos_y - 124) / 41, size);
		}
		return true;
	}

	void Player2Model::take_enemy_move(int pos_x, int pos_y, int type, int size) {
		if (type == 1) {
			_player_field[(pos_y - 124) / 41][(pos_x - 788) / 41] = 7;
			
		}
		else if (type == 2) {
			_player_field[(pos_y - 124) / 41][(pos_x - 788) / 41] = 8;
		}
		else if (type == 3) {
			_player_field[(pos_y - 124) / 41][(pos_x - 788) / 41] = 8;
			_circle_ship(_player_field, (pos_x - 788) / 41, (pos_y - 124) / 41, size);
		}
	}

	std::vector<std::vector<int>> Player2Model::get_enemy_field() const {
		return _enemy_field;
	}

}