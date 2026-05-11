import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom";
import { getToken } from "../../logics/localstorage";

function GameManage() {
    const [games, setGames] = useState([])
    const navigate = useNavigate()

    useEffect(() => {
        // Simulate an API call to fetch games
        const fetchGames = async () => {
            // Replace this with your actual API call
            const response = await fetch('/api/games');
            const data = await response.json();
            setGames(data);
        };

        fetchGames();
        
    }, []);

    async function handleDelete(id){

        try{

        const response = await fetch( `/api/games/${id}`,
                {
                    method: "DELETE",

                    headers: {
                        "Authorization": `Bearer ${getToken()}`
                    }
                }
            );

            if(!response.ok){
                throw new Error("Delete failed");
            }

            console.log("Disabled game:", id);

            setGames(prev =>
                prev.filter(game => game.id !== id)
            );

        }catch(error){

            console.error(error);
        }
    }


    return (
        <div style={{ padding: "20px" }}>
            <h2>Game Management</h2>
            <button onClick={() => console.log('clicked')} >new game</button>
            <table border="1" cellPadding="10" style={{ width: "100%", marginTop: "20px", borderCollapse: "collapse" }}>
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Publisher</th>
                        <th>Release Date</th>
                        <th>Categories</th>
                        <th>Actions</th>
                    </tr>
                </thead>

                <tbody>
                    {games.map((game, idx) => (
                        <tr key={game.id}>
                            <td>{idx + 1}</td>
                            <td>{game.id}</td>
                            <td>{game.name}</td>
                            <td>{game.price}</td>
                            <td>{game.publisher}</td>
                            <td>{game.releaseDate}</td>
                            <td>
                                {game.categories?.join(", ")}
                            </td>

                            {/* ACTIONS COLUMN */}
                            <td style={{ display: "flex", gap: "10px" }}>
                                <button onClick={() => console.log("update")}>
                                    Update
                                </button>

                                <button onClick={() => handleDelete(game.id)}>
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default GameManage