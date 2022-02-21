#include "StatisticModel.h"

namespace Game {

	std::vector<std::pair<std::wstring, std::wstring>> StatisticModel::_read_stat(std::wifstream& file) const {
		std::wstring line, word;
		int i = 0;
		std::vector<std::pair<std::wstring, std::wstring>> ret_v;
		while (getline(file, line)) {
			if (i++ > 20) break;
			word.clear();
			std::pair<std::wstring, std::wstring> p;
			for (size_t i = 0; i < line.length(); i++) {
				if (line[i] != L',')
					word += line[i];
				else {
					p.first = word;
					word.clear();
				}
			}
			p.second = word;
			ret_v.push_back(p);
		}
		return ret_v;
	}

	StatisticModel::StatisticModel() { }

	std::wstring StatisticModel::get_statistic() const {
		std::wifstream in_file;
		const std::locale utf8_locale = std::locale(std::locale(), new std::codecvt_utf8<wchar_t>());
		in_file.open(_file_name.c_str());
		in_file.imbue(utf8_locale);

		if (!in_file.is_open()) {
			in_file.close();
			return L"DataBaseError :)";
		}

		std::vector<std::pair<std::wstring, std::wstring>> stat = _read_stat(in_file);

		std::wstring out_str;
		int i = 0;
		for (auto s : stat) {
			out_str += std::to_wstring(++i) + L") " + s.first + L" " + s.second + L"\n";
			if (i == 10) break;
		}
		in_file.close();
		return out_str;
	}

}