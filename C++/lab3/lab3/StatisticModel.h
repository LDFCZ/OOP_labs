#pragma once


#include <fstream>
#include <string>
#include <codecvt>
#include <vector>

namespace Game {

	class StatisticModel {
	private:
		std::string _file_name = "res/stat.csv";

		std::vector<std::pair<std::wstring, std::wstring>> _read_stat(std::wifstream&) const;
	public:
		StatisticModel();

		std::wstring get_statistic() const;
	};
}

