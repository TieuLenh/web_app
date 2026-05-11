import { useEffect, useState } from "react";

const Profile = () => {
    const [user, setUser] = useState(null);
    useEffect(() => {
        const fetchProfile = async () => {
            try {
                const response = await fetch(
                    "http://localhost:8080/api/me",
                    {
                        headers: {
                            Authorization:
                                `Bearer ${localStorage.getItem("token")}`
                        }
                    }
                );
                const data = await response.json();
                console.log(data);
                setUser(data);
            } catch (error) {
                console.error(error);
            }
        };
        fetchProfile();
    }, []);
    if (!user) {
        return <h2>Loading...</h2>;
    }
    return (
        <div>
            <h1>Profile</h1>
            <p>Email: {user.email}</p>
            <p>Username: {user.username}</p>
        </div>
    );
};

export default Profile;