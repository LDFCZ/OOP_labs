#pragma once

#include "Worker.h"
#include "Context.h"

namespace MainWorkers {

    class ReaderWorker : public Interfaces::Worker {
    private:
        std::wstring _file_name;
        std::wifstream _file;

        MainContext::Context& _context;

        const Interfaces::Types _in_type = Interfaces::Types::EMPTY;
        const Interfaces::Types _out_type = Interfaces::Types::TEXT;
    public:
        ReaderWorker(std::vector<std::wstring>&, MainContext::Context&);

        ~ReaderWorker();

        void work() override;

        Interfaces::Types get_input_type() const override;

        Interfaces::Types get_output_type() const override;
    };
}
