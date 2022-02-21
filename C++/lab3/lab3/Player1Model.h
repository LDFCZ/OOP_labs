#pragma once

#include <fstream>
#include <string>
#include <codecvt>
#include <vector>
#include <algorithm>

namespace Game {

	class Player1Model {
	private:
		std::vector<std::vector<int>> _player_field;
		std::vector<std::vector<int>> _enemy_field;

		std::string _file_name = "res/stat.csv";

		std::wstring _name;

		std::vector<std::pair<std::wstring, int>> _read_stat(std::wifstream&) const;

		void _circle_ship(std::vector<std::vector<int>>&, int, int, int);

	public:

		void set_field(const std::vector<std::vector<int>>);

		std::vector<std::vector<int>> get_field() const;

		std::vector<std::vector<int>> get_enemy_field() const;

		void set_name(const std::wstring);

		void write_statistic();

		bool check_move(int, int);

		bool make_move(int, int, int, int = 0);

		void take_enemy_move(int, int, int, int = 0);
	};
}

