#pragma once

#include <fstream> 
#include <string>
#include <vector>

namespace FileInterface {

	class FileReader
	{
	private:
		std::string file_name;
		std::wifstream file;

	public:
		FileReader(const std::string& file_name);

		~FileReader();

		FileReader& operator >> (std::wstring& val);

		int is_EOF();
	};
}
