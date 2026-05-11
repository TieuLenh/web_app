import '../styles/card.css';

const Card = ({ game }) => {
    return (
        <div className="card h-100 shadow-sm game-card">

            {/* IMAGE */}
            <div className="ratio ratio-4x3 bg-dark">
                <img
                    src={game.imageUrl}
                    className="card-img-top object-fit-cover"
                    alt={game.name}
                    onError={(e) => {
                        e.target.src = "https://via.placeholder.com/300x400?text=No+Image";
                    }}
                />
            </div>

            {/* BODY */}
            <div className="card-body d-flex flex-column">

                <h5 className="card-title">{game.name}</h5>

                <p className="card-text small text-muted mb-2">
                    {game.shortDescription}
                </p>

                <div className="mt-auto">

                    <div className="d-flex justify-content-between align-items-center">

                        <span className="badge bg-secondary">
                            {game.categories?.[0] || "Game"}
                        </span>

                        <span className="fw-bold text-success">
                            {game.price === 0
                                ? "Free"
                                : game.price.toLocaleString("vi-VN") + " đ"}
                        </span>

                    </div>

                </div>
            </div>
        </div>
    );
};

export default Card;