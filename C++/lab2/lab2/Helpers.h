#pragma once

#include <string>
#include <codecvt>

namespace Helpers {
	inline std::string convert_to_string(std::wstring str) {
		std::wstring_convert<std::codecvt_utf8<wchar_t>> converter;
		std::string out = converter.to_bytes(str);
		return out;
	}
}