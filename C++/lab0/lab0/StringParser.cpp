#include "StringParser.h"
#include "ConstSpace.h"

namespace MainClasses {

	StringParser::StringParser(Container::Context& words_context, FileInterface::FileReader* ifile) :words_context(words_context), ifile(ifile)
	{}

	void StringParser::parse_strings() {
		std::wstring str;
		while (!ifile->is_EOF())
		{
			*this->ifile >> str;
			str += ConstSpace::ENDL;
			std::wstring word = ConstSpace::EMPTY_WSTR;
			for (size_t i = 0; i < str.length(); i++) {
				if (ConstSpace::ENG_ALPHABET_START_LOW <= str[i] && str[i] <= ConstSpace::ENG_ALPHABET_END_LOW ||
					ConstSpace::ENG_ALPHABET_START_HIGH <= str[i] && str[i] <= ConstSpace::ENG_ALPHABET_END_HIGH ||
					ConstSpace::RUS_ALPHABET_START_LOW <= str[i] && str[i] <= ConstSpace::RUS_ALPHABET_END_LOW ||
					ConstSpace::RUS_ALPHABET_START_HIGH <= str[i] && str[i] <= ConstSpace::RUS_ALPHABET_END_HIGH ||
					ConstSpace::NUM_ALPHABET_START <= str[i] && str[i] <= ConstSpace::NUM_ALPHABET_END) {
					word += str[i];
				}
				else {
					if (word.length()) {
						this->words_context.add_word(word);
						word = ConstSpace::EMPTY_WSTR;
					}
				}
			}
		}
	}
}