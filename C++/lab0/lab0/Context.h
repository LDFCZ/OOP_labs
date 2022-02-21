#pragma once

#include <map>
#include <string>
#include <vector>
#include <algorithm>

namespace Container {

	class Context
	{
	private:
		std::map<std::wstring, int> dictionary;
		int words_count;
	public:
		Context();

		~Context();

		void add_word(const std::wstring& word);

		std::vector<std::wstring> get_sorted_keys();

		int get_value_by_key(const std::wstring& key);

		int get_count();
	};
}
