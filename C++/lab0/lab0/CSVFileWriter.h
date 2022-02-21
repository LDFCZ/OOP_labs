#pragma once

#include <fstream>
#include <string>

namespace FileInterface {

	class CSVFileWriter;

	inline static CSVFileWriter& endrow(CSVFileWriter& ofile);

	class CSVFileWriter
	{
	private:
		std::string file_name;
		std::wofstream file;
		bool is_first;
	public:
		CSVFileWriter(const std::string& file_name);

		~CSVFileWriter();

		CSVFileWriter& operator << (CSVFileWriter& (*val)(CSVFileWriter&));

		CSVFileWriter& operator << (const std::wstring& val);

		CSVFileWriter& operator << (const wchar_t* val);

		CSVFileWriter& operator << (const int val);

		CSVFileWriter& operator << (const float val);

		void endrow();

		template<typename T>
		CSVFileWriter& operator << (const T& val);
	private:
		template<typename T>
		CSVFileWriter& write(const T& val);
	};

	inline static CSVFileWriter& endrow(CSVFileWriter& ofile)
	{
		ofile.endrow();
		return ofile;
	}
}