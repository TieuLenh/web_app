import '../styles/searchBar.css';
const SearchBar = () => {

    return (

        <div className="search-bar">

            <input
                type="text"
                placeholder="Search..."
            />

            <button>

                🔍

            </button>

        </div>
    );
}

export default SearchBar;