#include "Context.h"

namespace Container {

	Context::Context() {
		this->words_count = 0;
	}

	Context::~Context() {
		this->dictionary.clear();
	}

	void Context::add_word(const std::wstring& word) {
		if (this->dictionary.count(word)) {
			this->dictionary[word]++;
		}
		else {
			this->dictionary[word] = 1;
		}
		this->words_count++;
	}

	std::vector<std::wstring> Context::get_sorted_keys() {
		std::vector<std::wstring> keys;
		//get all keys
		for (std::map<std::wstring, int>::const_iterator it = this->dictionary.begin();
			it != this->dictionary.end();
			it++) {
			keys.push_back(it->first);
		}
		//sort them by value
		std::sort(keys.begin(), keys.end(),
			[this](const std::wstring& a, const std::wstring& b) {
				return this->dictionary[a] > this->dictionary[b];
			});

		return keys;
	}

	int Context::get_value_by_key(const std::wstring& key) {
		return this->dictionary[key];
	}

	int Context::get_count() {
		return this->words_count;
	}
}