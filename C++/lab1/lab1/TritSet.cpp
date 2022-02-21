#include "TritSet.h"
#include "CONSTSPACE.h"

#include <iostream>

namespace TernaryLogic {

	// Returns logical size of Trit Set
	size_t TritSet::size() const {
		return _logical_size;
	}

	// Capacity....
	size_t TritSet::capacity() const {
		return _array_size;
	}

	// Shrink....
	void TritSet::shrink() {
		if (!_alloc_size) return;

		long long last_index = _search_last_index();

		_change_TritSet_size(last_index);
		_array_size = (last_index + CONSTSPACE::LEVELER);
		_logical_size = last_index + CONSTSPACE::LEVELER;
	}

	// Trim....
	void TritSet::trim(size_t last_index) {

		_change_TritSet_size(last_index);

		size_t uint_pos = last_index / (CONSTSPACE::UINT_SIZE * CONSTSPACE::BYTE_SIZE / CONSTSPACE::TRIT_SIZE);

		uint shift = (CONSTSPACE::UINT_SIZE * CONSTSPACE::BYTE_SIZE / - (last_index + CONSTSPACE::LEVELER) * CONSTSPACE::TRIT_SIZE);

		_array[uint_pos] = (_array[uint_pos]  >> shift) << shift;
		_logical_size = _search_last_index() + CONSTSPACE::LEVELER;
	}

	// Cardinality....
	size_t TritSet::cardinality(Trit trit) const {
		if (trit == TRUE) return _true_count;
		else if (trit == FALSE) return  _false_count;
		else return _unknown_count;
	}

	// Cardinality.... but map.... unordered....
	std::unordered_map<Trit, size_t, std::hash<size_t>> TritSet::cardinality() const {
		std::unordered_map<Trit, size_t, std::hash<size_t>> unordered_map;
		unordered_map[TRUE] = _true_count;
		unordered_map[FALSE] = _false_count;
		unordered_map[UNKNOWN] = _unknown_count;
		return unordered_map;
	}



	// TritSet constructor
	TritSet::TritSet(size_t size, Trit trit) : _array_size(size), _logical_size(trit == UNKNOWN ? 0 : size) {
		////////////////////////////////////////////
		if (trit == TRUE) _true_count = size;
		else if (trit == FALSE) _false_count = size;
		else _unknown_count = size;
		////////////////////////////////////////////
		
		_alloc_size = size ? _get_alloc_size(size) : 0;

		uint mask = 0;

		for (size_t i = 0; i < CONSTSPACE::UINT_SIZE * CONSTSPACE::BYTE_SIZE / CONSTSPACE::TRIT_SIZE; i++)
			mask |= trit << i * CONSTSPACE::TRIT_SIZE;


		//Fill elements of uint array with mask
		for (size_t i = 0; i < _alloc_size; i++)
			_array.push_back(mask);
		// Remove unnecessary
		if (size) this->trim(_array_size - CONSTSPACE::LEVELER); 
	}

	// Copy constructor
	TritSet::TritSet(const TritSet& trit_set) : 
		_alloc_size(trit_set._alloc_size),
		_logical_size(trit_set._logical_size),
		_array_size(trit_set._array_size),

		_true_count(trit_set._true_count),
		_false_count(trit_set._false_count),
		_unknown_count(trit_set._unknown_count)
	{
		for (size_t i = 0; i < _alloc_size; i++)
		{
			_array.push_back(0);
		}
		for (size_t i = 0; i < _logical_size; i++)
		{
			_set_Trit(trit_set._get_Trit(i), i);
		}
	}

	TritSet& TritSet::operator = (const TritSet& trit_set) {
		if (this == &trit_set)
			return *this;

		_array.clear();

		_alloc_size = trit_set._alloc_size;
		_array_size = trit_set._array_size;
		_logical_size = trit_set._logical_size;

		_true_count = trit_set._true_count;
		_false_count = trit_set._false_count;
		_unknown_count = trit_set._unknown_count;

		for (size_t i = 0; i < _alloc_size; i++)
		{
			_array.push_back(0);
		}
		for (size_t i = 0; i < _logical_size; i++)
		{
			_set_Trit(trit_set._get_Trit(i), i);
		}

		return *this;
	}

	// Move constructor
	TritSet::TritSet(TritSet&& trit_set) :
		_alloc_size(trit_set._alloc_size),
		_logical_size(trit_set._logical_size),
		_array_size(trit_set._array_size),
		_array(trit_set._array),

		_true_count(trit_set._true_count),
		_false_count(trit_set._false_count),
		_unknown_count(trit_set._unknown_count)
	{
		trit_set._alloc_size = 0;
		trit_set._array_size = 0;
		trit_set._logical_size = 0;

		trit_set._true_count = 0;
		trit_set._false_count = 0;
		trit_set._unknown_count = 0;

		trit_set._array.clear();
	}

	TritSet& TritSet::operator=(TritSet&& trit_set) {
		if (this == &trit_set)
			return *this;

		 _array.clear();

		_alloc_size = trit_set._alloc_size;
		_array_size = trit_set._array_size;
		_logical_size = trit_set._logical_size;

		_true_count = trit_set._true_count;
		_false_count = trit_set._false_count;
		_unknown_count = trit_set._unknown_count;
		
		_array = trit_set._array;

		trit_set._alloc_size = 0;
		trit_set._array_size = 0;
		trit_set._logical_size = 0;

		trit_set._true_count = 0;
		trit_set._false_count = 0;
		trit_set._unknown_count = 0;

		trit_set._array.clear();

		return *this;
	}


	//+-+-+-+-+-+-+-+-+-+-+-+-+-+- for operations like set[10] = TRUE and other ... +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
	TritSet::Reference::Reference(TritSet& set, size_t position) : _set(set), _position(position) 
	{}


	TritSet::Reference TritSet::operator [] (size_t index) {
		return Reference(*this, index);
	}

	TritSet& TritSet::Reference::operator = (Trit trit) {
		_set._set_Trit(trit, _position);
		return _set;
	}

	Trit TritSet::operator[](size_t position) const {
		return _get_Trit(position);
	}

	// Convertion to Trit type
	TritSet::Reference::operator Trit() const {
		return _set._get_Trit(_position);
	}
	//+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-


	// Returns right size of future uint array
	long long TritSet::_get_alloc_size(long long array_size) {
		if (array_size < 0) return 0;
		return array_size * CONSTSPACE::TRIT_SIZE / CONSTSPACE::BYTE_SIZE / CONSTSPACE::UINT_SIZE + CONSTSPACE::LEVELER;
	}

	// Set the value of the trit at a certain position
	void TritSet::_set_Trit(Trit trit, size_t position) {
		
		
		if (position >= _array_size) {
			if (trit == UNKNOWN) return;
			_change_TritSet_size(position);
		}
		size_t uint_pos = position / (CONSTSPACE::UINT_SIZE * CONSTSPACE::BYTE_SIZE / CONSTSPACE::TRIT_SIZE);
		uint trit_pos = position  % (CONSTSPACE::UINT_SIZE * CONSTSPACE::BYTE_SIZE / CONSTSPACE::TRIT_SIZE);
		uint value_with_shift = ((uint)trit << (trit_pos * CONSTSPACE::TRIT_SIZE));

		size_t old_trit = _array[uint_pos] >> (trit_pos * CONSTSPACE::TRIT_SIZE) & CONSTSPACE::MASK;

		////////////////////////////////////////////
		if (old_trit == TRUE)  _true_count--;
		else if (old_trit == FALSE) _false_count--;
		else _unknown_count--;

		if (trit == TRUE)  _true_count++;
		else if (trit == FALSE) _false_count++;
		else _unknown_count++;
		////////////////////////////////////////////

		// Here we directly change the trit value in tritSet by index
		_array[uint_pos] = static_cast<uint>(((~(CONSTSPACE::MASK << (trit_pos * CONSTSPACE::TRIT_SIZE))) & _array[uint_pos]) | value_with_shift);
		if (position >= _logical_size && trit != UNKNOWN) _logical_size = position + CONSTSPACE::LEVELER;
		if (trit == UNKNOWN) _logical_size = _search_last_index() + CONSTSPACE::LEVELER;
	}

	// Changing TritSet size (up and down)
	void TritSet::_change_TritSet_size(long long new_position) {

		size_t new_alloc_size = _get_alloc_size(new_position);
		if (new_alloc_size > _alloc_size)
		{
			for (size_t i = 0; i < new_alloc_size - _alloc_size; i++) {
				_array.push_back(0);
			}
		}
		else
		{
			for (size_t i = 0; i < _alloc_size - new_alloc_size; i++) {
				_array.pop_back();
			}
		}
		_alloc_size = new_alloc_size;
		_array_size = new_position + CONSTSPACE::LEVELER;
	}

	

	// Returns Trit in current position
	Trit TritSet::_get_Trit(size_t position) const {
		if (position >= _array_size) return UNKNOWN;
		size_t uint_pos = position / (CONSTSPACE::UINT_SIZE * CONSTSPACE::BYTE_SIZE / CONSTSPACE::TRIT_SIZE);
		uint trit_pos = position % (CONSTSPACE::UINT_SIZE * CONSTSPACE::BYTE_SIZE / CONSTSPACE::TRIT_SIZE);
		return Trit((_array[uint_pos] >> (trit_pos * CONSTSPACE::TRIT_SIZE)) & CONSTSPACE::MASK);
	}

	// Searching last significant index in TritSet
	long long TritSet::_search_last_index() const {
		long long last_index = -1;
		for (size_t i = 0; i < _array_size; i++) {
			if (_get_Trit(i) != UNKNOWN)
				last_index = i;
		}
		return last_index;
	}



	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Overloading logical expressions for TritSets~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	//Operator '&' overloading for TritSet
	TritSet operator&(const TritSet& trit_set_1, const TritSet& trit_set_2) {
		size_t new_size = trit_set_1.size() > trit_set_2.size() ? trit_set_1.size() : trit_set_2.size();
		TritSet new_trit_set(new_size);

		for (size_t i = 0; i < new_size; i++) 
			new_trit_set[i] = trit_set_1[i] & trit_set_2[i];	

		return new_trit_set;
	}

	//Operator '|' overloading for TritSet
	TritSet operator|(const TritSet& trit_set_1, const TritSet& trit_set_2) {
		size_t new_size = trit_set_1.size() > trit_set_2.size() ? trit_set_1.size() : trit_set_2.size();

		TritSet new_trit_set(new_size);

		for (size_t i = 0; i < new_size; i++) 
			new_trit_set[i] = trit_set_1[i] | trit_set_2[i];
				
		return new_trit_set;
	}

	//Operator '~' overloading for TritSet
	TritSet operator~(const TritSet& trit_set) {
		TritSet new_trit_set(trit_set.size());

		for (size_t i = 0; i < trit_set.size(); i++)
			new_trit_set[i] = ~trit_set[i];
		return new_trit_set;
	}

	//Operator '==' overloading for TritSet
	bool operator==(const TritSet& trit_set_1, const TritSet& trit_set_2) {
		if (trit_set_1.size() != trit_set_2.size()) return false;

		for (size_t i = 0; i < trit_set_1.size(); i++)
			if (trit_set_1[i] != trit_set_2[i]) return false;

		return true;
	}

	//Operator '!=' overloading for TritSet
	bool operator!=(const TritSet& trit_set_1, const TritSet& trit_set_2) {
		return !(trit_set_1 == trit_set_2);
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

}
