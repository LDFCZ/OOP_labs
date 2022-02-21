#include "FileReader.h"
#include "ConstSpace.h"

#include <codecvt>

namespace FileInterface {

	FileReader::FileReader(const std::string& file_name) {
		this->file_name = file_name;
		const std::locale utf8_locale = std::locale(std::locale(), new std::codecvt_utf8<wchar_t>());
		this->file.open(this->file_name);
		this->file.imbue(utf8_locale);
		if (!this->file.is_open()) {
			std::string error_text = ConstSpace::ERR_STR + this->file_name;
			throw error_text;
		}
	}

	FileReader::~FileReader() {
		this->file.close();
	}

	FileReader& FileReader::operator >> (std::wstring& val) {
		getline(this->file, val);
		return *this;
	}

	int FileReader::is_EOF() {
		return this->file.eof();
	}
}