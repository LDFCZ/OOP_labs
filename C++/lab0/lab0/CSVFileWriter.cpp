#include "CSVFileWriter.h"
#include "ConstSpace.h"

#include <codecvt>

namespace FileInterface {

    CSVFileWriter::CSVFileWriter(const std::string& file_name) {
        this->file_name = file_name;
        const std::locale utf8_locale = std::locale(std::locale(), new std::codecvt_utf8<wchar_t>());
        this->file.exceptions(std::ios::failbit | std::ios::badbit);
        this->file.open(this->file_name);
        this->file.imbue(utf8_locale);
        this->is_first = true;
    }

    CSVFileWriter::~CSVFileWriter() {
        this->file.close();
    }

    CSVFileWriter& CSVFileWriter::operator << (CSVFileWriter& (*val)(CSVFileWriter&))
    {
        return val(*this);
    }

    CSVFileWriter& CSVFileWriter::operator << (const std::wstring& val) {
        return write(val);
    }

    CSVFileWriter& CSVFileWriter::operator << (const wchar_t* val) {
        return write(val);
    }

    CSVFileWriter& CSVFileWriter::operator << (const int val) {
        return write(std::to_wstring(val));
    }

    CSVFileWriter& CSVFileWriter::operator << (const float val) {
        return write(std::to_wstring(val));
    }

    void CSVFileWriter::endrow() {
        this->file << std::endl;
        this->is_first = true;
    }

    template<typename T>
    CSVFileWriter& CSVFileWriter::operator << (const T& val) {
        return write(val);
    }

    template<typename T>
    CSVFileWriter& CSVFileWriter::write(const T& val) {
        if (!is_first) {
            this->file << ConstSpace::SEPARATOR;
        }
        else {
            is_first = false;
        }
        this->file << val;
        return *this;
    }
}